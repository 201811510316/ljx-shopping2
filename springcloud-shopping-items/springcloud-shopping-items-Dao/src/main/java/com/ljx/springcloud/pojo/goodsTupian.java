package com.ljx.springcloud.pojo;

public class goodsTupian extends goodsImg{

    //商品名称
    private String goodsName;

    public goodsTupian() {
    }

    public goodsTupian(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }
}
