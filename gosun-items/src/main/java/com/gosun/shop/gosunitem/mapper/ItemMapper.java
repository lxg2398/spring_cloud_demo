package com.gosun.shop.gosunitem.mapper;

import com.gosun.shop.gosunitem.domain.Item;
import tk.mybatis.mapper.common.Mapper;

/**
 * 商品数据持久层
 */
@org.apache.ibatis.annotations.Mapper
public interface ItemMapper extends Mapper<Item> {
}
