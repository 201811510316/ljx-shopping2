package com.ljx.springcloud.service;

import com.ljx.springcloud.pojo.goodsAd;

import java.util.List;

public interface goodsAdService {

    //展示全部商品广告信息
    List<goodsAd> queryByGoodsAd();

    //查看单个商品广告信息
    goodsAd queryById(Integer id);

    //添加广告
    Integer queryByAddGoodsAd(goodsAd goodsAd);

    //删除商品广告
    Integer queryByGoodsAdDelete(Integer id);
}
