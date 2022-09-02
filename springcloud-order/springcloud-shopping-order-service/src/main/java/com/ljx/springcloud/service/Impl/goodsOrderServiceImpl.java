package com.ljx.springcloud.service.Impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.ljx.springcloud.mapper.goodsOrderMapper;
import com.ljx.springcloud.mapper.orderItemMapper;
import com.ljx.springcloud.pojo.goodsOrder;
import com.ljx.springcloud.pojo.goodsSum;
import com.ljx.springcloud.pojo.orderItem;
import com.ljx.springcloud.service.goodsOrderService;
import com.ljx.springcloud.utils.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.*;

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
        return new PageResult<>(goodsOrders.getTotal(),goodsOrders.getPages(),result);
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
        return new PageResult<>(orderItems.getTotal(),orderItems.getPages(),result);
    }

    //统计已支付的商品总数量
    @Override
    public List<goodsSum> queryByGoodsCount() {
        //创建商品数量统计
        List<goodsSum> goodsSums = new ArrayList<>();
        //存储已支付订单
        List<goodsOrder> goodsPays = new ArrayList<>();
        //获取全部订单,将已支付订单存储起来
        List<goodsOrder> goodsOrders = goodsOrderMapper.selectAll();
        for(goodsOrder goodsOrder:goodsOrders){
            if(goodsOrder.getPayStatus()!=0 && goodsOrder.getPayTime()!=null){
                goodsPays.add(goodsOrder);
            }
        }
        //遍历goodsPays 得到商品信息并存储在list<goodsSum>集合中
        for(goodsOrder goodsPay: goodsPays){
            Example example = new Example(goodsOrder.class);
            Example.Criteria criteria = example.createCriteria();
            criteria.andEqualTo("orderNo",goodsPay.getOrderNo());
            List<orderItem> orderItems = orderItemMapper.selectByExample(example);
            for(orderItem orderItem:orderItems){
                goodsSum goodsSum = new goodsSum();
                goodsSum.setGoodsId(orderItem.getGoodsId());
                goodsSum.setGoodsName(orderItem.getGoodsName());
                goodsSum.setCount(orderItem.getCount());
                goodsSums.add(goodsSum);
            }
        }
        //将list<goodsSum>集合中相同的商品进行相加
        //先创建map集合
        Map<goodsSum,goodsSum> map = new HashMap<>();
        for(goodsSum goodsSum1:goodsSums){
           if(map.containsKey(goodsSum1)){
              map.put(goodsSum1,goodsSum.merge(goodsSum1,map.get(goodsSum1)));
           }else{
               map.put(goodsSum1,goodsSum1);
           }
        }
        List<goodsSum> goodsCount = new ArrayList<>();
        for(goodsSum key :map.values()){
            goodsCount.add(key);
        }
        return goodsCount;
    }

    //统计所有已支付订单的总价格
    @Override
    public Integer queryByGoodsSum() {
        int totalCount=0;
        //存储已支付订单
        List<goodsOrder> goodsPays = new ArrayList<>();
        //获取全部订单,将已支付订单存储起来
        List<goodsOrder> goodsOrders = goodsOrderMapper.selectAll();
        for(goodsOrder goodsOrder:goodsOrders){
            if(goodsOrder.getPayStatus()!=0 && goodsOrder.getPayTime()!=null){
                goodsPays.add(goodsOrder);
            }
        }
        for(goodsOrder goodsOrder:goodsPays){
            totalCount+=goodsOrder.getTotalPrice();
        }
        return totalCount;
    }
}
