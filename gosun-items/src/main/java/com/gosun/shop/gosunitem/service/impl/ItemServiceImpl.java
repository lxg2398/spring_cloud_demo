package com.gosun.shop.gosunitem.service.impl;

import com.codingapi.txlcn.tc.annotation.LcnTransaction;
import com.gosun.shop.gosunitem.bo.OrderBO;
import com.gosun.shop.gosunitem.domain.Item;
import com.gosun.shop.gosunitem.mapper.ItemMapper;
import com.gosun.shop.gosunitem.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ItemServiceImpl implements ItemService {

    @Autowired(required = false)
    private ItemMapper itemMapper;

    /**
     * 减库存操作
     * @param orderBO
     * @return
     */
    @Override
    //@Transactional //表示使用的是本地事务，不能保证跨服务事务原子性。
    @LcnTransaction //加入分布式事务注解（自带本地事务，所以@Transactional可以不要了）
    public Integer deecreaseStock(OrderBO orderBO) {
        //判断扣减库存的商品是否存在
        Item items = itemMapper.selectByPrimaryKey(orderBO.getSkuId());
        if (items == null){
            throw new RuntimeException("购买的商品不存在！！！");
        }

        //获取商品的原库存
        int counts = items.getCounts();
        if (counts - orderBO.getNum() < 0){
            throw new RuntimeException("商品库存不足！！！");
        }

        //执行减库存操作
        Item goods = new Item();
        //需要减库存的id
        goods.setId(orderBO.getSkuId());
        goods.setCounts(counts - orderBO.getNum());

        //更新
        int row = itemMapper.updateByPrimaryKeySelective(goods);
        return row;
    }
}
