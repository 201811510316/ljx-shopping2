package com.ljx.springcloud.pojo;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name="goods_slide")
public class goodsSlide {

    //轮播图id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    //商品id（外键）
    private Integer goodsId;

    //轮播图图片
    private String slideImg;

    public goodsSlide() {
    }

    public goodsSlide(Integer id, Integer goodsId, String slideImg) {
        this.id = id;
        this.goodsId = goodsId;
        this.slideImg = slideImg;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    public String getSlideImg() {
        return slideImg;
    }

    public void setSlideImg(String slideImg) {
        this.slideImg = slideImg;
    }

    @Override
    public String toString() {
        return "goodsSlide{" +
                "id=" + id +
                ", goodsId=" + goodsId +
                ", slideImg='" + slideImg + '\'' +
                '}';
    }
}
