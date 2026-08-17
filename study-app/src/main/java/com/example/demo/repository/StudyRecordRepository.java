package com.example.demo.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.entity.StudyRecord;

public interface StudyRecordRepository
        extends JpaRepository<StudyRecord, Integer> {

    // 今日の合計
    @Query("""
        SELECT COALESCE(SUM(s.studyTime), 0)
        FROM StudyRecord s
        WHERE s.studyDate = CURRENT_DATE
    """)
    Integer getTodayTotal();

    // 今週の合計
    @Query("""
        SELECT COALESCE(SUM(s.studyTime), 0)
        FROM StudyRecord s
        WHERE s.studyDate >= :startDate
    """)
    Integer getWeekTotal(@Param("startDate") LocalDate startDate);

    // 今月の合計
    @Query("""
        SELECT COALESCE(SUM(s.studyTime), 0)
        FROM StudyRecord s
        WHERE s.studyDate >= :startMonth
    """)
    Integer getMonthTotal(@Param("startMonth") LocalDate startMonth);

    // カレンダー用（★重要：これが完成ポイント）
    List<StudyRecord> findByStudyDateBetween(
            LocalDate start,
            LocalDate end
    );
}