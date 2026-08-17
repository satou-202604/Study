package com.example.demo.dto;

import java.time.LocalDate;

import org.antlr.v4.runtime.misc.NotNull;

import com.example.demo.entity.Priority;

import jakarta.validation.constraints.NotBlank;

public class TodoForm {

    private Integer id;

    @NotBlank
    private String title;

    @NotNull
    private Priority priority;

    @NotNull
    private LocalDate deadline;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    public LocalDate getDeadline() {
        return deadline;
    }

    public void setDeadline(LocalDate deadline) {
        this.deadline = deadline;
    }
}