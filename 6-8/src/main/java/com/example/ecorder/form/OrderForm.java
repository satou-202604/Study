package com.example.ecorder.form;

import java.time.LocalDate;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class OrderForm {

    private Integer orderId;

    @NotBlank(message = "受注番号は必須です")
    @Size(max = 20, message = "受注番号は20文字以内で入力してください")
    private String orderNo;

    @NotBlank(message = "購入者名は必須です")
    @Size(max = 50, message = "購入者名は50文字以内で入力してください")
    private String customerName;

    @NotNull(message = "商品を選択してください")
    private Integer productId;

    @NotNull(message = "数量を入力してください")
    @Min(value = 1, message = "数量は1以上で入力してください")
    @Max(value = 999, message = "数量は999以下で入力してください")
    private Integer quantity;

    @NotBlank(message = "ステータスを選択してください")
    @Size(max = 20, message = "ステータスは20文字以内で入力してください")
    private String orderStatus;

    @NotNull(message = "受注日は必須です")
    private LocalDate orderDate;

    private LocalDate deliveryDate;


    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }

    public LocalDate getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(LocalDate deliveryDate) {
        this.deliveryDate = deliveryDate;
    }
}