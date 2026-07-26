package com.example.order.repository;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.example.order.entity.OrderDetailEntity;

@Mapper
public interface OrderDetailMapper {

    @Select("SELECT id, order_id, product_id, product_name, unit_price, quantity, subtotal, created_at " +
            "FROM order_details WHERE order_id = #{orderId}")
    List<OrderDetailEntity> findByOrderId(@Param("orderId") Integer orderId);

    @Insert("INSERT INTO order_details (order_id, product_id, product_name, unit_price, quantity, subtotal, created_at) " +
            "VALUES (#{orderId}, #{productId}, #{productName}, #{unitPrice}, #{quantity}, #{subtotal}, #{createdAt})")
    void insert(OrderDetailEntity detail);
}