package com.example.demo.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.demo.dao.UserMapper;
import com.example.demo.entity.UserEntity;

/**
 * UserService 単体試験
 */
@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    /**
     * Mapper モック
     */
    @Mock
    private UserMapper userMapper;

    /**
     * テスト対象
     */
    @InjectMocks
    private UserService userService;
    
    /**
     * searchAll テスト
     */
    @Test
    public void testSearchAll() {

    	// テスト用データ
        List<UserEntity> userList = new ArrayList<>();  
    
        UserEntity user = new UserEntity();
        user.setId(1);
        user.setName("山田太郎");

        userList.add(user);

        // モック動作設定
        when(userMapper.findAll()).thenReturn(userList);

        // 実行
        List<UserEntity> result = userService.searchAll();

        // 確認
        assertEquals(1, result.size());
        assertEquals("山田太郎", result.get(0).getName());
    }
    
    
    	/**
         * findById テスト
         */
        @Test
        public void testFindById() {

            // テスト用データ
            UserEntity user = new UserEntity();
            user.setId(1);
            user.setName("佐藤花子");

            // モック動作設定
            when(userMapper.getOne(1)).thenReturn(user);

            // 実行
            UserEntity result = userService.findById(1);

            // 確認
            assertEquals(1, result.getId());
            assertEquals("佐藤花子", result.getName());
        }
   
}
    