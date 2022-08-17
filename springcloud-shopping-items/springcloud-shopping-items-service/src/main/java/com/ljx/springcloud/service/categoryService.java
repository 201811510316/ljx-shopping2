package com.ljx.springcloud.service;

import com.ljx.springcloud.pojo.goodsClassification;
import java.util.List;

public interface categoryService {

    //以分页-查询商品分类的种类
    List<goodsClassification> queryListByCategory();

    //根据商品id获取对应的商品分类名称
    goodsClassification queryById(Integer id);

}
