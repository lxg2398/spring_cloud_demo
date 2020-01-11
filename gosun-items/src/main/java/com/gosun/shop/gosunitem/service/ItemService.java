package com.gosun.shop.gosunitem.service;

import com.gosun.shop.gosunitem.bo.OrderBO;

public interface ItemService {
    /**
     * 减库存的方法
     */
    Integer deecreaseStock(OrderBO orderBO);
}
