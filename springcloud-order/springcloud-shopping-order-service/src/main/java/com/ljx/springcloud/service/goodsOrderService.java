package com.ljx.springcloud.service;

import com.ljx.springcloud.pojo.goodsOrder;
import com.ljx.springcloud.pojo.orderItem;
import com.ljx.springcloud.utils.PageResult;

public interface goodsOrderService {

    //查看全部订单信息
    PageResult<goodsOrder> queryByGoodsOrders(Integer page, Integer rows);

    //根据已支付的订单，来为订单发货（修改订单状态）
    Boolean queryByGoodsOrderUpdate(String id);

    //删除订单
    void queryByOrderDelete(String id);

    //查看每个订单的详情商品信息
    PageResult<orderItem> queryByOrderItems(Integer page, Integer rows,String id);

}
