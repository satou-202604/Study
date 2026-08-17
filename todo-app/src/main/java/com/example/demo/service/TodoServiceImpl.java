package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.example.demo.dto.TodoForm;
import com.example.demo.entity.Todo;
import com.example.demo.repository.TodoRepository;

@Service
public class TodoServiceImpl implements TodoService {

    @Autowired
    private TodoRepository todoRepository;

    @Override
    public Page<Todo> findTodos(
            String filter,
            String sort,
            String keyword,
            int page) {

        return Page.empty();
    }

    @Override
    public Todo findById(Integer id) {

        return todoRepository.findById(id).orElse(null);
    }

    @Override
    public void addTodo(TodoForm form) {

    }

    @Override
    public void updateTodo(TodoForm form) {

    }

    @Override
    public void deleteTodo(Integer id) {

        todoRepository.deleteById(id);
    }

    @Override
    public void toggleTodo(Integer id) {

    }

    @Override
    public long countCompleted() {

        return todoRepository.countByStatus(true);
    }

    @Override
    public long countUndone() {

        return todoRepository.countByStatus(false);
    }

}