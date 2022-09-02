package com.ljx.springcloud.controller;


import com.ljx.springcloud.pojo.goodsOrder;
import com.ljx.springcloud.pojo.goodsSum;
import com.ljx.springcloud.pojo.orderItem;
import com.ljx.springcloud.service.goodsOrderService;
import com.ljx.springcloud.utils.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class orderController {

    @Autowired
    goodsOrderService goodsOrderService;

    //查看全部订单信息
    @GetMapping("/order/all")
    public ResponseEntity<PageResult<goodsOrder>> queryByOrderAll(@RequestParam(value = "page",defaultValue = "1")Integer page,
                                                                  @RequestParam(value = "rows",defaultValue = "4")Integer rows){
        PageResult<goodsOrder> result = goodsOrderService.queryByGoodsOrders(page, rows);
        if(result==null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(result);
    }

    //根据已支付的订单，来为订单发货（修改订单状态）
    @GetMapping("/order/orderId")
    public ResponseEntity<Boolean> queryByOrderId(@RequestParam("id")String id){
        Boolean aBoolean = goodsOrderService.queryByGoodsOrderUpdate(id);
        if(aBoolean){
            return ResponseEntity.ok(aBoolean);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    //删除订单
    @DeleteMapping("/order/delete")
    public ResponseEntity<Void> queryByDelete(@RequestParam("id") String id){
        try {
            goodsOrderService.queryByOrderDelete(id);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //查看每个订单的详情商品信息
    @GetMapping("/order/goods")
    public ResponseEntity<PageResult<orderItem>> queryByGoods(@RequestParam(value = "page",defaultValue = "1")Integer page,
                                                              @RequestParam(value = "rows",defaultValue = "4")Integer rows,
                                                              @RequestParam("id")String id){
        PageResult<orderItem> result = goodsOrderService.queryByOrderItems(page, rows, id);
        if(result==null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(result);
    }

    //统计已支付订单的所有商品数量
    @GetMapping("/order/count")
    public ResponseEntity<List<goodsSum>> queryByCount(){
        List<goodsSum> goodsSums = goodsOrderService.queryByGoodsCount();
        if(!goodsSums.isEmpty()){
            return ResponseEntity.ok(goodsSums);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    //统计所有已支付订单的总价格
    @GetMapping("/order/price")
    public ResponseEntity<Integer> queryByPrice(){
        Integer integer = goodsOrderService.queryByGoodsSum();
        return ResponseEntity.ok(integer);
    }

}
