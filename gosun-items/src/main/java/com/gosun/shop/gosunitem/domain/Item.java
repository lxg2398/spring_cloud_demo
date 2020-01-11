package com.gosun.shop.gosunitem.domain;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;

/**
 * 商品实体
 */
@Data
public class Item {
    @Id
    private String id;//商品id
    private String name;//商品名称
    @Column(name = "counts")
    private int counts;//商品库存
}
