package com.ljx.springcloud.service;

import com.ljx.springcloud.pojo.goodsSlide;

import java.util.List;

public interface goodsSlideService {

    //查看单个商品轮播图信息
    List<goodsSlide> queryBySelectSlide(Integer id);
}
