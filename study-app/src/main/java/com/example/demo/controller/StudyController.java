package com.example.demo.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entity.StudyRecord;
import com.example.demo.repository.StudyRecordRepository;

@Controller
public class StudyController {

    @Autowired
    private StudyRecordRepository repository;

    // トップ画面
    @GetMapping("/")
    public String index(Model model) {

        // 勉強記録一覧取得
        List<StudyRecord> records =
                repository.findAll();

        // 今日の勉強時間
        Integer todayTotal =
                repository.getTodayTotal();

        // 今週開始日
        LocalDate startWeek =
                LocalDate.now().minusDays(7);

        // 今月開始日
        LocalDate startMonth =
                LocalDate.now().withDayOfMonth(1);

        // 今週の勉強時間
        Integer weekTotal =
                repository.getWeekTotal(startWeek);

        // 今月の勉強時間
        Integer monthTotal =
                repository.getMonthTotal(startMonth);

        // HTMLへ渡す
        model.addAttribute(
                "records",
                records
        );

        model.addAttribute(
                "todayTotal",
                todayTotal == null ? 0 : todayTotal
        );

        model.addAttribute(
                "weekTotal",
                weekTotal == null ? 0 : weekTotal
        );

        model.addAttribute(
                "monthTotal",
                monthTotal == null ? 0 : monthTotal
        );

        return "index";
    }

    // 登録処理
    @PostMapping("/save")
    public String save(

            @RequestParam String subject,

            @RequestParam Integer studyTime,

            @RequestParam String memo

    ) {

        StudyRecord record =
                new StudyRecord();

        record.setSubject(subject);

        record.setStudyTime(studyTime);

        record.setMemo(memo);

        record.setStudyDate(LocalDate.now());

        repository.save(record);

        return "redirect:/";
    }

    // 削除処理
    @PostMapping("/delete")
    public String delete(
            @RequestParam Integer id
    ) {

        repository.deleteById(id);

        return "redirect:/";
    }
}