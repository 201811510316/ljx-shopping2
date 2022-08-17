package com.ljx.springcloud.service;

import com.ljx.springcloud.pojo.goods;
import com.ljx.springcloud.utils.PageResult;
import com.ljx.springcloud.pojo.goodsFenLei;

public interface goodsService {

    //以分页方式展示商品信息
    PageResult<goodsFenLei> queryByGoods(Integer page, Integer rows, Integer saleable);

    //保存商品
    void save(goodsFenLei goodsFenLei);

    //查询单个商品信息（用于修改回显）
    goodsFenLei queryGoodsById(Integer id);

    //保存修改商品信息
    void update(goods goods);

    //删除商品
    void deleteByGoods(Integer id);
}
