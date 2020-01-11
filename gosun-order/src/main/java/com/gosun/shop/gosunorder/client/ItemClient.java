package com.gosun.shop.gosunorder.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Map;

@FeignClient(name = "gosun-item")
public interface ItemClient {

    @PostMapping("item/{skuId}/{count}")
    public Map<String,Object> decreaseStock(@PathVariable("skuId") String skuId, @PathVariable("count") int count);
}
