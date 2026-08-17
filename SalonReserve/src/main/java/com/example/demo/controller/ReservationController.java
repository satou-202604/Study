package com.example.demo.controller;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entity.Reservation;
import com.example.demo.service.ReservationService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/reservations")
public class ReservationController {

    @Autowired
    private ReservationService service;

    // 一覧
    @GetMapping
    public String list(Model model) {

        model.addAttribute(
                "reservations",
                service.findAll());

        return "reservation/list";
    }

    // 新規登録画面
    @GetMapping("/new")
    public String createForm(Model model) {

        model.addAttribute(
                "reservation",
                new Reservation());

        return "reservation/form";
    }

    // 登録・更新
    @PostMapping
    public String save(
            @Valid @ModelAttribute Reservation reservation,
            BindingResult result,
            Model model) {

        if (result.hasErrors()) {
            return "reservation/form";
        }

        try {

            service.save(reservation);

        } catch (IllegalArgumentException e) {

            model.addAttribute(
                    "errorMessage",
                    e.getMessage());

            return "reservation/form";
        }

        return "redirect:/reservations";
    }

    // 編集
    @GetMapping("/edit/{id}")
    public String edit(
            @PathVariable Integer id,
            Model model) {

        model.addAttribute(
                "reservation",
                service.findById(id));

        return "reservation/form";
    }

    // 削除
    @GetMapping("/delete/{id}")
    public String delete(
            @PathVariable Integer id) {

        service.deleteById(id);

        return "redirect:/reservations";
    }

    // 会員番号・お客様名検索
    @GetMapping("/search")
    public String search(
            @RequestParam String keyword,
            Model model) {

        model.addAttribute(
                "reservations",
                service.search(keyword));

        return "reservation/list";
    }

    // 日付検索
    @GetMapping("/search/date")
    public String searchDate(
            @RequestParam LocalDate date,
            Model model) {

        model.addAttribute(
                "reservations",
                service.findByDate(date));

        return "reservation/list";
    }

    // カレンダー表示
    @GetMapping("/calendar")
    public String calendar(
            @RequestParam(required = false) Integer year,
            @RequestParam(required = false) Integer month,
            Model model) {

        YearMonth ym = (year == null || month == null)
                ? YearMonth.now()
                : YearMonth.of(year, month);

        var reservations = service.findAll()
                .stream()
                .filter(r ->
                        YearMonth.from(
                                r.getReservationDate())
                                .equals(ym))
                .toList();

        Map<Integer, Long> calendarMap =
                reservations.stream()
                        .collect(Collectors.groupingBy(
                                r -> r.getReservationDate()
                                        .getDayOfMonth(),
                                Collectors.counting()));

        model.addAttribute("year", ym.getYear());
        model.addAttribute("month", ym.getMonthValue());
        model.addAttribute("calendar", calendarMap);

        return "reservation/calendar";
    }
}