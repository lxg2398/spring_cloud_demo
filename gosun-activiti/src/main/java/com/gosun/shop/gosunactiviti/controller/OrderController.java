package com.gosun.shop.gosunactiviti.controller;

import com.gosun.shop.gosunactiviti.domain.Orders;
import com.gosun.shop.gosunactiviti.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * 订单Controller
 */
@RestController
@RequestMapping("order")
public class OrderController {

    @Autowired(required = false)
    private OrderService orderService;

    /**
     * 根据ID查询订单的接口
     */
    @GetMapping("{id}")
    public Orders getOrderById(@PathVariable("id") String id){
         return orderService.getOrderInfoById(id);
    }

//    @PostMapping("create")
//    public Map<String,Object> createOrder(@RequestBody OrderBO orderBO){
//        Integer row = orderService.createOrder(orderBO);
//
//        Map<String,Object> map = new HashMap<>();
//
//        map.put("code",row > 0 ? "111111" : "000000");//设置一个状态码
//        map.put("msg",row > 0 ? "success" : "fail");
//        return map;
//    }
}
