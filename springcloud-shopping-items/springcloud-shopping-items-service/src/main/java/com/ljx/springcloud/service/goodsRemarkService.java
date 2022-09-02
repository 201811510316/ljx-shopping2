package com.ljx.springcloud.service;

import com.ljx.springcloud.pojo.goodsRemark;
import com.ljx.springcloud.utils.PageResult;

public interface goodsRemarkService {

    //查看
    PageResult<goodsRemark> findByGoodsRemark(Integer page, Integer rows);

    //添加
    Integer findByAddRemark(goodsRemark goodsRemark);

    //查看单个商品的评价
    goodsRemark findBySelectId(Integer id);

    //修改
    Integer findByUpdateRemark(goodsRemark goodsRemark);

    //删除
    Integer findDeleteRemark(Integer id);
}
