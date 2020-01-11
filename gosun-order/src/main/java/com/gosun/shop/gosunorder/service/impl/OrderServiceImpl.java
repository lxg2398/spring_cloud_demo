package com.gosun.shop.gosunorder.service.impl;

import com.codingapi.txlcn.tc.annotation.LcnTransaction;
import com.gosun.shop.gosunorder.bo.OrderBO;
import com.gosun.shop.gosunorder.client.ItemClient;
import com.gosun.shop.gosunorder.domain.Orders;
import com.gosun.shop.gosunorder.mapper.OrderMapper;
import com.gosun.shop.gosunorder.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;
import java.util.UUID;

/**
 * 订单实现层
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired(required = false)
    private OrderMapper orderMapper;

    @Autowired(required = false)
    private ItemClient itemClient;

    @Override
    public Orders getOrderInfoById(String id) {
        return orderMapper.selectByPrimaryKey(id);
    }

    /**
     * 创建订单的业务方法，该方法需要注意的是，要避免传入一些敏感数据，如金额等。
     * @param orderBO
     * @return
     */
    @Override
    //@Transactional //表示使用的是本地事务，不能保证跨服务事务原子性。
    @LcnTransaction
    public Integer createOrder(OrderBO orderBO) {

        int row = 0;

        //远程调用商品微服务，进行减库存的操作
        Map<String, Object> map = itemClient.decreaseStock(orderBO.getSkuId(), orderBO.getNum());
        if (map.get("code").equals("000000")){
            throw new RuntimeException("减库存失败"+map.get("msg"));
        }

        //减库存之后，再创建订单
        //1、创建一个订单对象
        Orders orders = new Orders();
        //订单号
        orders.setId(UUID.randomUUID().toString().replace("-","").toUpperCase());
        //下单号
        orders.setOrderNum(UUID.randomUUID().toString().replace("-","").toUpperCase());
        //商品id
        orders.setItemId(orderBO.getSkuId());
        //2、把订单信息持久化
        row = orderMapper.insert(orders);

        return row;
    }
}
