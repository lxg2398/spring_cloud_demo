package com.gosun.shop.gosunorder.service;

import com.gosun.shop.gosunorder.bo.OrderBO;
import com.gosun.shop.gosunorder.domain.Orders;

/**
 * service接口
 */
public interface OrderService {
    Orders getOrderInfoById(String id);
    /**
     * 创建订单方法
     */
    Integer createOrder(OrderBO orderBO);
}
