package com.ljx.springcloud.service;

import com.ljx.springcloud.pojo.goodsAd;

import java.util.List;

public interface goodsAdService {

    List<goodsAd> queryByGoodsAd();

    goodsAd queryById(Integer id);

    //添加广告
    Integer queryByAddGoodsAd(goodsAd goodsAd);
}