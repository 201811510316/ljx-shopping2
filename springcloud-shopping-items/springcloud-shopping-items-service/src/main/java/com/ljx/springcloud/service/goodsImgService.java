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

    //保存商品图片修改的信息
    void save(goodsImg goodsImg);

    //保存添加图片
    void goodsImgAdd(goodsImg goodsImg);

}
