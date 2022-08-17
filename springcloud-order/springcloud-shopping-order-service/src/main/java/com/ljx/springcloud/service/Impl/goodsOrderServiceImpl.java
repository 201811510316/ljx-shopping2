package com.ljx.springcloud.service.Impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.ljx.springcloud.mapper.goodsOrderMapper;
import com.ljx.springcloud.mapper.orderItemMapper;
import com.ljx.springcloud.pojo.goodsOrder;
import com.ljx.springcloud.pojo.orderItem;
import com.ljx.springcloud.service.goodsOrderService;
import com.ljx.springcloud.utils.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;
import java.util.List;

@Service
public class goodsOrderServiceImpl implements goodsOrderService {

    @Autowired
    goodsOrderMapper goodsOrderMapper;

    @Autowired
    orderItemMapper orderItemMapper;


    //查看全部订单信息
    @Override
    public PageResult<goodsOrder> queryByGoodsOrders(Integer page, Integer rows) {
        PageHelper.startPage(page,rows);
        Page<goodsOrder> goodsOrders = (Page<goodsOrder>) goodsOrderMapper.selectAll();
        List<goodsOrder> result = goodsOrders.getResult();
        return new PageResult<>(goodsOrders.getTotal(),result);
    }

    //根据已支付的订单，来为订单发货（修改订单状态）
    @Override
    public Boolean queryByGoodsOrderUpdate(String id) {
        Example example = new Example(goodsOrder.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("orderNo",id);
        goodsOrder goodsOrder = goodsOrderMapper.selectOneByExample(example);
        //判断是否支付
        if(goodsOrder.getPayStatus()==0){
            return false;
        }else{
            goodsOrder.setOrderState(1);
            goodsOrder.setUpdateTime(new Date());
            goodsOrderMapper.updateByPrimaryKeySelective(goodsOrder);
            return true;
        }
    }

    //删除订单
    @Override
    public void queryByOrderDelete(String id) {
        Example example = new Example(goodsOrder.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("orderNo",id);
        goodsOrderMapper.deleteByExample(example);
    }

    //查看每个订单的详情商品信息
    @Override
    public PageResult<orderItem> queryByOrderItems(Integer page, Integer rows, String id) {
        PageHelper.startPage(page,rows);
        Example example = new Example(orderItem.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("orderNo",id);
        Page<orderItem> orderItems = (Page<orderItem>) orderItemMapper.selectByExample(example);
        List<orderItem> result = orderItems.getResult();
        return new PageResult<>(orderItems.getTotal(),result);
    }
}
