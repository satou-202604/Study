package com.example.demo.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Priority;
import com.example.demo.entity.Todo;

public interface TodoRepository extends JpaRepository<Todo, Integer> {

    /* ========= フィルター ========= */

    List<Todo> findByStatus(Boolean status);

    List<Todo> findByPriority(Priority priority);

    List<Todo> findByDeadlineBefore(LocalDate date);

    List<Todo> findByTitleContainingIgnoreCase(String keyword);

    List<Todo> findByStatusAndTitleContainingIgnoreCase(Boolean status, String keyword);

    /* ========= ソート ========= */

    List<Todo> findAllByOrderByDeadlineAsc();

    List<Todo> findAllByOrderByPriorityAsc();

    List<Todo> findAllByOrderByCreatedAtDesc();

    /* ========= ページング ========= */

    Page<Todo> findAll(Pageable pageable);

    /* ========= 件数取得 ========= */

    long countByStatus(Boolean status);

}