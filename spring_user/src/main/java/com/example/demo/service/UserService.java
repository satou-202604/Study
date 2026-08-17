package com.example.demo.service;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dao.UserMapper;
import com.example.demo.dto.UserRequest;
import com.example.demo.dto.UserUpdateRequest;
import com.example.demo.entity.GenderEntity;
import com.example.demo.entity.UserEntity;

/**
 * ユーザー情報 Service
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class UserService {

    private static final String DUPLICATE_EMAIL_MESSAGE =
            "同じメールアドレスのユーザーが既に存在します。";

    /**
     * ユーザー情報 Mapper
     */
    @Autowired
    private UserMapper userMapper;

    /**
     * ユーザー情報 全検索
     * @return 検索結果
     */
    public List<UserEntity> searchAll() {

        return userMapper.findAll();
    }

    /**
     * ユーザー情報 主キー検索
     * @param id ユーザーID
     * @return 検索結果
     */
    public UserEntity findById(Integer id) {

        return userMapper.getOne(id);
    }

    /**
     * ユーザー情報 新規登録
     * @param userRequest リクエストデータ
     */
    public void create(UserRequest userRequest) {

        // メールアドレス重複チェック
        int emailCount =
                userMapper.countByEmail(userRequest.getEmail());

        if (emailCount != 0) {
            throw new IllegalArgumentException(
                    DUPLICATE_EMAIL_MESSAGE);
        }

        Date now = new Date();

        UserEntity user = new UserEntity();

        user.setName(userRequest.getName());
        user.setAddress(userRequest.getAddress());
        user.setPhone(userRequest.getPhone());
        user.setEmail(userRequest.getEmail());
        user.setGenderId(userRequest.getGenderId());

        user.setCreateDate(now);
        user.setUpdateDate(now);

        userMapper.userSave(user);
    }

    /**
     * ユーザー情報 更新
     * @param userUpdateRequest リクエストデータ
     */
    public void update(UserUpdateRequest userUpdateRequest) {

        // 自分以外のメールアドレス重複チェック
        int emailCount =
                userMapper.countByEmailExcludeId(
                        userUpdateRequest.getId(),
                        userUpdateRequest.getEmail());

        if (emailCount != 0) {
            throw new IllegalArgumentException(
                    DUPLICATE_EMAIL_MESSAGE);
        }

        // 更新対象ユーザー取得
        UserEntity user =
                findById(userUpdateRequest.getId());

        // 入力内容を更新
        user.setName(userUpdateRequest.getName());
        user.setAddress(userUpdateRequest.getAddress());
        user.setPhone(userUpdateRequest.getPhone());
        user.setEmail(userUpdateRequest.getEmail());
        user.setGenderId(userUpdateRequest.getGenderId());

        // 更新日時
        user.setUpdateDate(new Date());

        // 更新実行
        userMapper.userUpdate(user);
    }

    /**
     * 性別マスタをMapで取得
     * @return genderMap
     */
    public Map<Integer, String> getGenderMap() {

        List<GenderEntity> genders =
                userMapper.findAllGender();

        Map<Integer, String> genderMap =
                new LinkedHashMap<Integer, String>();

        for (GenderEntity gender : genders) {

            Integer genderId = gender.getGenderId();
            String genderName = gender.getGenderName();

            if (genderId != null) {
                genderMap.put(genderId, genderName);
            }
        }

        return genderMap;
        
        
    }
    /**
     * ユーザー情報 論理削除
     * @param id ユーザーID
     */
    public void delete(Integer id) {
        userMapper.userDelete(id);
    }
    
}

