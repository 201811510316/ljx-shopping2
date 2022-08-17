package com.ljx.springcloud.service.Impl;

import com.ljx.springcloud.mapper.goodsClassificationMapper;
import com.ljx.springcloud.pojo.goodsClassification;
import com.ljx.springcloud.service.categoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class categoryServiceImpl implements categoryService {

    @Autowired
    goodsClassificationMapper goodsClassificationMapper;

    //以分页-查询商品分类的种类
    @Override
    public List<goodsClassification> queryListByCategory() {
        goodsClassification goodsClassification = new goodsClassification();
        List<goodsClassification> select = goodsClassificationMapper.select(goodsClassification);
        return select;
    }

    //根据商品id获取对应的商品分类名称
    @Override
    public goodsClassification queryById(Integer id) {
        goodsClassification goodsClassification = goodsClassificationMapper.selectByPrimaryKey(id);
        return goodsClassification;
    }
}
