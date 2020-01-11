package com.gosun.shop.gosunitem.controller;

import com.gosun.shop.gosunitem.bo.OrderBO;
import com.gosun.shop.gosunitem.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * 商品接口
 */
@RestController
@RequestMapping("item")
public class ItemController {

    @Autowired(required = false)
    private ItemService itemService;

    @PostMapping("{skuId}/{count}")
    public Map<String,Object> decreaseStock(@PathVariable("skuId") String skuId, @PathVariable("count") int count){
        Map<String, Object> map = new HashMap<>();
        //设置参数
        OrderBO orderBO = new OrderBO();
        orderBO.setSkuId(skuId);
        orderBO.setNum(count);
        //调用减库存的方法
        Integer row = itemService.deecreaseStock(orderBO);

        map.put("code",row > 0 ? "111111" : "000000");//设置一个状态码
        map.put("msg",row > 0 ? "success" : "fail");//设置一个消息

        return map;
    }
}
