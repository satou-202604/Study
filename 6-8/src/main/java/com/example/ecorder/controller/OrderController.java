package com.example.ecorder.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.ecorder.entity.OrderEntity;
import com.example.ecorder.exception.OrderDuplicateException;
import com.example.ecorder.form.OrderForm;
import com.example.ecorder.service.OrderService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/orders")
public class OrderController {

private final OrderService orderService;

public OrderController(OrderService orderService) {
    this.orderService = orderService;
}

// 受注一覧
@GetMapping
public String index(Model model) {

    model.addAttribute("orders", orderService.findAll());

    return "orders/index";
}

// 新規登録画面
@GetMapping("/new")
public String newForm(Model model) {

    model.addAttribute("orderForm", new OrderForm());
    model.addAttribute("products", orderService.findProducts());

    return "orders/form";
}

// 登録
@PostMapping
public String create(
        @Valid @ModelAttribute("orderForm") OrderForm form,
        BindingResult bindingResult,
        Model model) {

    if (bindingResult.hasErrors()) {
        model.addAttribute("products", orderService.findProducts());
        return "orders/form";
    }

    try {
        OrderEntity order = toEntity(form);
        orderService.insert(order);

    } catch (OrderDuplicateException e) {
        model.addAttribute("errorMessage", e.getMessage());
        model.addAttribute("products", orderService.findProducts());
        return "orders/form";
    }

    return "redirect:/orders";
}

// 編集画面
@GetMapping("/{id}/edit")
public String editForm(
        @PathVariable("id") Integer id,
        Model model) {

    OrderEntity order = orderService.findById(id);

    if (order == null) {
        return "redirect:/orders";
    }

    OrderForm form = toForm(order);

    model.addAttribute("orderForm", form);
    model.addAttribute("products", orderService.findProducts());

    return "orders/form";
}

// 更新
@PostMapping("/{id}/update")
public String update(
        @PathVariable("id") Integer id,
        @Valid @ModelAttribute("orderForm") OrderForm form,
        BindingResult bindingResult,
        Model model) {

    form.setOrderId(id);

    if (bindingResult.hasErrors()) {
        model.addAttribute("products", orderService.findProducts());
        return "orders/form";
    }

    try {
        OrderEntity order = toEntity(form);
        orderService.update(order);

    } catch (OrderDuplicateException e) {
        model.addAttribute("errorMessage", e.getMessage());
        model.addAttribute("products", orderService.findProducts());
        return "orders/form";
    }

    return "redirect:/orders";
}

// 論理削除
@PostMapping("/{id}/delete")
public String delete(@PathVariable("id") Integer id) {

    orderService.delete(id);

    return "redirect:/orders";
}

// Form → Entity
private OrderEntity toEntity(OrderForm form) {

    OrderEntity order = new OrderEntity();

    order.setOrderId(form.getOrderId());
    order.setOrderNo(form.getOrderNo());
    order.setCustomerName(form.getCustomerName());
    order.setProductId(form.getProductId());
    order.setQuantity(form.getQuantity());
    order.setOrderStatus(form.getOrderStatus());
    order.setOrderDate(form.getOrderDate());
    order.setDeliveryDate(form.getDeliveryDate());

    return order;
}

// Entity → Form
private OrderForm toForm(OrderEntity order) {

    OrderForm form = new OrderForm();

    form.setOrderId(order.getOrderId());
    form.setOrderNo(order.getOrderNo());
    form.setCustomerName(order.getCustomerName());
    form.setProductId(order.getProductId());
    form.setQuantity(order.getQuantity());
    form.setOrderStatus(order.getOrderStatus());
    form.setOrderDate(order.getOrderDate());
    form.setDeliveryDate(order.getDeliveryDate());

    return form;
}

}
