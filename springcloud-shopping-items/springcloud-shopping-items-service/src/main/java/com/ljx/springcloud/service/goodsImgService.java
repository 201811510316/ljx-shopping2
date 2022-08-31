package com.ljx.springcloud.service;

import com.ljx.springcloud.pojo.goodsImg;
import com.ljx.springcloud.pojo.goodsTupian;
import com.ljx.springcloud.utils.PageResult;

import java.util.List;

public interface goodsImgService {

    //查询商品详情图片
    PageResult<goodsTupian> qureyByGoodsTupian(Integer page, Integer rows);

    //根据id查询对应商品详情图片
    goodsImg queryByGoodsImg(Integer id);


}
