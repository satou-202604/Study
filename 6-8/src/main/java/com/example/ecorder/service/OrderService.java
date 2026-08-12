package com.example.ecorder.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.ecorder.entity.OrderEntity;
import com.example.ecorder.exception.OrderDuplicateException;
import com.example.ecorder.mapper.OrderMapper;

@Service
public class OrderService {

    private final OrderMapper orderMapper;

    public OrderService(OrderMapper orderMapper) {
        this.orderMapper = orderMapper;
    }

    // 受注一覧取得
    public List<OrderEntity> findAll() {
        return orderMapper.findAll();
    }

    // 受注1件取得
    public OrderEntity findById(Integer orderId) {

        OrderEntity order = orderMapper.findById(orderId);

        System.out.println("===== findById 確認 =====");

        if (order == null) {
            System.out.println("order = null");
        } else {
            System.out.println("orderId = " + order.getOrderId());
            System.out.println("orderNo = " + order.getOrderNo());
            System.out.println("customerName = " + order.getCustomerName());
            System.out.println("productId = " + order.getProductId());
            System.out.println("productName = " + order.getProductName());
            System.out.println("quantity = " + order.getQuantity());
            System.out.println("orderStatus = " + order.getOrderStatus());
            System.out.println("orderDate = " + order.getOrderDate());
            System.out.println("deliveryDate = " + order.getDeliveryDate());
        }

        System.out.println("======================");

        return order;
    }

    // 商品一覧取得
    public Map<Integer, OrderEntity> findProducts() {

    Map<Integer, OrderEntity> products = orderMapper.findProducts();

    System.out.println("===== 商品一覧確認 =====");
    System.out.println("件数: " + products.size());

    for (Map.Entry<Integer, OrderEntity> entry : products.entrySet()) {

        OrderEntity product = entry.getValue();

        System.out.println(
            "productId=" + entry.getKey()
            + ", productName=" + product.getProductName()
        );
    }

    System.out.println("======================");

 
   return products;
}

    // 受注登録
    @Transactional
    public void insert(OrderEntity order) {

        int count = orderMapper.countDuplicate(order);

        if (count > 0) {
            throw new OrderDuplicateException(
                "同一購入者・同一商品・同一受注日の受注は登録できません。"
            );
        }

        orderMapper.insert(order);
    }

    // 受注更新
    @Transactional
    public void update(OrderEntity order) {

        int count = orderMapper.countDuplicate(order);

        if (count > 0) {
            throw new OrderDuplicateException(
                "同一購入者・同一商品・同一受注日の受注は登録できません。"
            );
        }

        orderMapper.update(order);
    }

    // 論理削除
    @Transactional
    public void delete(Integer orderId) {
        orderMapper.delete(orderId);
    }
}
