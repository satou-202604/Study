package com.example.order.service;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.order.dto.response.OrderDetailResponse;
import com.example.order.dto.response.OrderResponse;
import com.example.order.entity.OrderEntity;
import com.example.order.exception.InvalidOrderStatusException;
import com.example.order.exception.OrderNotFoundException;
import com.example.order.repository.OrderDetailMapper;
import com.example.order.repository.OrderMapper;

@Service
public class OrderService {

    private static final Logger logger = LoggerFactory.getLogger(OrderService.class);

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private OrderDetailMapper orderDetailMapper;

    public OrderResponse findById(Integer id) {
        logger.info("注文検索: id={}", id);

        OrderEntity order = orderMapper.findById(id)
                .orElseThrow(() -> new OrderNotFoundException(id));

        List<OrderDetailResponse> details = orderDetailMapper.findByOrderId(id)
                .stream()
                .map(OrderDetailResponse::from)
                .collect(Collectors.toList());

        return OrderResponse.from(order, details);
    }

    public List<OrderResponse> findByCustomer(Integer customerId) {
        logger.info("顧客別注文取得: customerId={}", customerId);

        return orderMapper.findByCustomer(customerId)
                .stream()
                .map(order -> {
                    List<OrderDetailResponse> details = orderDetailMapper
                            .findByOrderId(order.getId())
                            .stream()
                            .map(OrderDetailResponse::from)
                            .collect(Collectors.toList());

                    return OrderResponse.from(order, details);
                })
                .collect(Collectors.toList());
    }

    @Transactional
    public void ship(Integer orderId) {
        logger.info("出荷処理: orderId={}", orderId);

        // 存在しない注文は404(Not Found)
        OrderEntity order = orderMapper.findById(orderId)
                .orElseThrow(() -> new OrderNotFoundException(orderId));

        // ステータスチェック
        if (!"処理中".equals(order.getStatus())) {
            throw new InvalidOrderStatusException(
                    order.getStatus(),
                    "処理中");
        }

        // 出荷済みに更新
        orderMapper.updateStatus(orderId, "出荷済み");

        logger.info("出荷処理完了: orderId={}", orderId);
    }

    @Transactional
    public void cancel(Integer orderId) {
        logger.info("注文キャンセル: orderId={}", orderId);

        OrderEntity order = orderMapper.findById(orderId)
                .orElseThrow(() -> new OrderNotFoundException(orderId));

        if ("出荷済み".equals(order.getStatus())
                || "完了".equals(order.getStatus())) {
            throw new InvalidOrderStatusException(
                    order.getStatus(),
                    "受注済みまたは処理中");
        }

        orderMapper.updateStatus(orderId, "キャンセル");

        logger.info("注文キャンセル完了: orderId={}", orderId);
    }
}