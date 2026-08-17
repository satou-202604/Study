package com.example.demo.service;

import org.springframework.data.domain.Page;

import com.example.demo.dto.TodoForm;
import com.example.demo.entity.Todo;

public interface TodoService {

    Page<Todo> findTodos(
            String filter,
            String sort,
            String keyword,
            int page);

    Todo findById(Integer id);

    void addTodo(TodoForm form);

    void updateTodo(TodoForm form);

    void deleteTodo(Integer id);

    void toggleTodo(Integer id);

    long countCompleted();

    long countUndone();

}