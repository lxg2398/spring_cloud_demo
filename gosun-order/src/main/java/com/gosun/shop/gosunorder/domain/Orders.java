package com.gosun.shop.gosunorder.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Id;

/**
 * @author Administrator
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Orders {
    @Id
    private String id;//订单编号
    private String orderNum;//下订单号
    private String itemId;//商品id
}
