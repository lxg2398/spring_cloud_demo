package com.gosun.shop.gosunorder.mapper;

import com.gosun.shop.gosunorder.domain.Orders;
import tk.mybatis.mapper.common.Mapper;

/**
 * 订单dao层
 */
@org.apache.ibatis.annotations.Mapper
public interface OrderMapper extends Mapper<Orders> {
}
