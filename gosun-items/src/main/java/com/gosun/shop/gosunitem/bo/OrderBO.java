package com.gosun.shop.gosunitem.bo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 订单的业务对象
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderBO {
    private String skuId;//商品ID
    private int num;//商品数量

    public int getNum(){
        return Math.abs(this.num);
    }
}
