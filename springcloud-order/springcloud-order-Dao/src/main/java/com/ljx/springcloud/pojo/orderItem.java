package com.ljx.springcloud.pojo;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 订单关联购物商品表
 */
@Table(name="order_item")
public class orderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String orderNo;

    private Integer goodsId;

    private String goodsName;

    private String tupian;

    private Integer count;

    private Integer goodsPrice;

    public orderItem() {
    }

    public orderItem(Integer id, String orderNo, Integer goodsId, String goodsName, String tupian, Integer count, Integer goodsPrice) {
        this.id = id;
        this.orderNo = orderNo;
        this.goodsId = goodsId;
        this.goodsName = goodsName;
        this.tupian = tupian;
        this.count = count;
        this.goodsPrice = goodsPrice;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public Integer getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getTupian() {
        return tupian;
    }

    public void setTupian(String tupian) {
        this.tupian = tupian;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Integer getGoodsPrice() {
        return goodsPrice;
    }

    public void setGoodsPrice(Integer goodsPrice) {
        this.goodsPrice = goodsPrice;
    }

    @Override
    public String toString() {
        return "orderItem{" +
                "id=" + id +
                ", orderNo='" + orderNo + '\'' +
                ", goodsId=" + goodsId +
                ", goodsName='" + goodsName + '\'' +
                ", tupian='" + tupian + '\'' +
                ", count=" + count +
                ", goodsPrice=" + goodsPrice +
                '}';
    }
}
