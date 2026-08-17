package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.dto.UserRequest;
import com.example.demo.dto.UserUpdateRequest;
import com.example.demo.entity.UserEntity;
import com.example.demo.service.UserService;

/**
 * ユーザー情報 Controller
 */
@Controller
public class UserController {

    /**
     * ユーザー情報 Service
     */
    @Autowired
    UserService userService;

    /**
     * ユーザー情報詳細画面を表示
     * @param id 表示するユーザーID
     * @param model Model
     * @return ユーザー情報詳細画面
     */
    @GetMapping("/user/{id}")
    public String userDetail(@PathVariable Integer id, Model model) {

        UserEntity user = userService.findById(id);

        model.addAttribute("userData", user);

        return "user/view";
    }

    /**
     * ユーザー一覧画面を表示
     * @param model Model
     * @return ユーザー一覧画面
     */
    @GetMapping("/user/list")
    public String userList(Model model) {

        model.addAttribute("userlist", userService.searchAll());

        return "user/list";
    }

    /**
     * ユーザー新規登録画面を表示
     * @param model Model
     * @return ユーザー新規登録画面
     */
    @RequestMapping("/user/add")
    public String userRegister(Model model) {

        // 性別をMapで取得（共通処理）
        Map<Integer, String> genderMap = setGenderMap();

        model.addAttribute("userRequest", new UserRequest());
        model.addAttribute("genderMap", genderMap);

        return "user/add";
    }

    /**
     * ユーザー新規登録
     * @param userRequest リクエストデータ
     * @param result バリデーション結果
     * @param model Model
     * @return ユーザー情報一覧画面
     */
    @PostMapping("/user/create")
    public String userCreate(
            @Validated @ModelAttribute UserRequest userRequest,
            BindingResult result,
            Model model) {

        // 性別をMapで取得（共通処理）
        Map<Integer, String> genderMap = setGenderMap();

        // 入力チェックエラー
        if (result.hasErrors()) {

            List<String> errorList = new ArrayList<String>();

            for (ObjectError error : result.getAllErrors()) {
                errorList.add(error.getDefaultMessage());
            }

            model.addAttribute("validationError", errorList);
            model.addAttribute("genderMap", genderMap);

            return "user/add";
        }

        // 業務エラー
        try {

            // ユーザー情報登録
            userService.create(userRequest);

        } catch (IllegalArgumentException e) {

            model.addAttribute("businessError", e.getMessage());
            model.addAttribute("genderMap", genderMap);

            return "user/add";
        }

        // 一覧画面へリダイレクト
        return "redirect:/user/list";
    }

    /**
     * ユーザー編集画面を表示
     * @param id 表示するユーザーID
     * @param model Model
     * @return ユーザー編集画面
     */
    @GetMapping("/user/{id}/edit")
    public String userEdit(@PathVariable Integer id, Model model) {

        // 性別をMapで取得（共通処理）
        Map<Integer, String> genderMap = setGenderMap();

        // 編集対象のユーザー情報を取得
        UserEntity user = userService.findById(id);

        // 編集画面用DTOへ詰め替え
        UserUpdateRequest userUpdateRequest = new UserUpdateRequest();

        userUpdateRequest.setId(user.getId());
        userUpdateRequest.setName(user.getName());
        userUpdateRequest.setAddress(user.getAddress());
        userUpdateRequest.setPhone(user.getPhone());
        userUpdateRequest.setEmail(user.getEmail());
        userUpdateRequest.setGenderId(user.getGenderId());

        model.addAttribute("userUpdateRequest", userUpdateRequest);
        model.addAttribute("genderMap", genderMap);

        return "user/edit";
    }

    /**
     * ユーザー更新
     * @param userUpdateRequest リクエストデータ
     * @param result バリデーション結果
     * @param model Model
     * @return ユーザー情報詳細画面
     */
    @RequestMapping("/user/update")
    public String userUpdate(
            @Validated @ModelAttribute UserUpdateRequest userUpdateRequest,
            BindingResult result,
            Model model) {

        // 性別をMapで取得（共通処理）
        Map<Integer, String> genderMap = setGenderMap();

        // 入力チェックエラー
        if (result.hasErrors()) {

            List<String> errorList = new ArrayList<String>();

            for (ObjectError error : result.getAllErrors()) {
                errorList.add(error.getDefaultMessage());
            }

            model.addAttribute("validationError", errorList);
            model.addAttribute("genderMap", genderMap);

            return "user/edit";
        }

        // 業務エラー
        try {

            // ユーザー情報更新
            userService.update(userUpdateRequest);

        } catch (IllegalArgumentException e) {

            model.addAttribute("businessError", e.getMessage());
            model.addAttribute("genderMap", genderMap);

            return "user/edit";
        }

        // 詳細画面へリダイレクト
        return String.format("redirect:/user/%d",
                userUpdateRequest.getId());
    }

    /**
     * ユーザー情報削除
     * @param id ユーザーID
     * @param model Model
     * @return ユーザー一覧画面
     */
    @GetMapping("/user/{id}/delete")
    public String userDelete(@PathVariable Integer id, Model model) {

        // ユーザー情報削除
        userService.delete(id);

        return "redirect:/user/list";
    }

    /**
     * 性別の値をMapで取得（key,value）
     * @return genderMap
     */
    private Map<Integer, String> setGenderMap() {

        return userService.getGenderMap();
    }
}