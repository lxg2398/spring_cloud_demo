package com.gosun.shop.gosunhrt.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Administrator
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Orders {
    private String id;//订单编号
    private String orderNum;//下订单号
    private String itemId;//商品id
}
