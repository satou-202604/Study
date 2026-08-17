package com.example.demo.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.entity.StudyRecord;
import com.example.demo.repository.StudyRecordRepository;

import jakarta.validation.Valid;

@Controller
public class StudyController {

    @Autowired
    private StudyRecordRepository repository;

    // 一覧表示
    @GetMapping("/")
    public String index(Model model) {

        List<StudyRecord> records = repository.findAll();

        model.addAttribute("records", records);
        model.addAttribute("studyRecord", new StudyRecord());

        return "index";
    }

    // 登録処理
    @PostMapping("/add")
    public String add(
            @Valid StudyRecord studyRecord,
            BindingResult result,
            Model model) {

        // バリデーションエラー
        if (result.hasErrors()) {

            List<StudyRecord> records = repository.findAll();

            model.addAttribute("records", records);

            return "index";
        }

        // 日付設定
        studyRecord.setStudyDate(LocalDate.now());

        // 保存
        repository.save(studyRecord);

        return "redirect:/";
    }
}