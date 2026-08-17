package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.dto.TodoForm;
import com.example.demo.entity.Todo;
import com.example.demo.service.TodoService;

import jakarta.validation.Valid;

@Controller
public class TodoController {

    @Autowired
    private TodoService todoService;

    /**
     * 一覧表示
     */
    @GetMapping("/")
    public String index(
            @RequestParam(required =false) String filter,
            @RequestParam(required =false) String sort,
            @RequestParam(required =false) String keyword,
            @RequestParam(defaultValue = "0") int page,
            Model model) {

        model.addAttribute("todoPage",
                todoService.findTodos(filter, sort, keyword, page));

        model.addAttribute("todoForm", new TodoForm());

        model.addAttribute("filter", filter);
        model.addAttribute("sort", sort);
        model.addAttribute("keyword", keyword);

        model.addAttribute("completedCount",
                todoService.countCompleted());

        model.addAttribute("undoneCount",
                todoService.countUndone());

        return "index";
    }

    /**
     * 新規追加
     */
    @PostMapping("/add")
    public String addTodo(
            @Valid @ModelAttribute TodoForm todoForm,
            BindingResult result,
            RedirectAttributes redirectAttributes) {

        if (result.hasErrors()) {
            redirectAttributes.addFlashAttribute(
                    "error",
                    "入力内容を確認してください。");
            return "redirect:/";
        }

        todoService.addTodo(todoForm);

        redirectAttributes.addFlashAttribute(
                "success",
                "タスクを追加しました。");

        return "redirect:/";
    }

    /**
     * 編集画面
     */
    @GetMapping("/edit/{id}")
    public String editPage(
            @PathVariable Integer id,
            Model model) {

        Todo todo = todoService.findById(id);

        model.addAttribute("todo", todo);

        return "edit";
    }

    /**
     * 更新
     */
    @PostMapping("/update")
    public String updateTodo(
            @Valid @ModelAttribute TodoForm todoForm,
            BindingResult result,
            RedirectAttributes redirectAttributes) {

        if (result.hasErrors()) {

            redirectAttributes.addFlashAttribute(
                    "error",
                    "入力内容に誤りがあります。");

            return "redirect:/edit/" + todoForm.getId();
        }

        todoService.updateTodo(todoForm);

        redirectAttributes.addFlashAttribute(
                "success",
                "更新しました。");

        return "redirect:/";
    }

    /**
     * 削除
     */
    @PostMapping("/delete")
    public String deleteTodo(
            @RequestParam Integer id,
            RedirectAttributes redirectAttributes) {

        todoService.deleteTodo(id);

        redirectAttributes.addFlashAttribute(
                "success",
                "削除しました。");

        return "redirect:/";
    }

    /**
     * 完了切替
     */
    @PostMapping("/toggle")
    public String toggleTodo(
            @RequestParam Integer id) {

        todoService.toggleTodo(id);

        return "redirect:/";
    }
}