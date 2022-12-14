package com.ljx.springcloud.service;

import com.ljx.springcloud.pojo.goods;
import com.ljx.springcloud.utils.PageResult;
import com.ljx.springcloud.pojo.goodsFenLei;

public interface goodsService {

    //以分页方式展示商品信息
    PageResult<goodsFenLei> queryByGoods(Integer page, Integer rows, Integer saleable);

    //保存商品
    Integer save(goods goods);

    //查询单个商品信息（用于修改回显）
    goods queryGoodsById(Integer id);

    //保存修改商品信息
    void update(goods goods);

    //删除商品
    Integer deleteByGoods(Integer id);
}
