package com.example.demo.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.entity.StudyRecord;

public interface StudyRecordRepository
        extends JpaRepository<StudyRecord, Integer> {

    // 今日の勉強時間
    @Query("""
        SELECT COALESCE(SUM(s.studyTime), 0)
        FROM StudyRecord s
        WHERE s.studyDate = CURRENT_DATE
    """)
    Integer getTodayTotal();

    // 今週の勉強時間
    @Query("""
        SELECT COALESCE(SUM(s.studyTime), 0)
        FROM StudyRecord s
        WHERE s.studyDate >= :startDate
    """)
    Integer getWeekTotal(
            LocalDate startDate
    );

    // 今月の勉強時間
    @Query("""
        SELECT COALESCE(SUM(s.studyTime), 0)
        FROM StudyRecord s
        WHERE EXTRACT(
            MONTH FROM s.studyDate
        ) =
        EXTRACT(
            MONTH FROM CURRENT_DATE
        )
    """)
    Integer getMonthTotal();

    // 教科検索
    List<StudyRecord>
        findBySubjectContaining(
            String keyword
    );

}