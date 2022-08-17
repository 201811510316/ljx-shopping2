package com.ljx.springcloud.service.Impl;

import com.ljx.springcloud.mapper.goodsAdMapper;
import com.ljx.springcloud.pojo.goodsAd;
import com.ljx.springcloud.service.goodsAdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class goodsAdServiceImpl implements goodsAdService {

    @Autowired
    goodsAdMapper goodsAdMapper;

    //查看广告
    @Override
    public List<goodsAd> queryByGoodsAd() {
        goodsAd goodsAd = new goodsAd();
        List<goodsAd> goodsAdList = goodsAdMapper.select(goodsAd);
        return goodsAdList;
    }

    //根据id查询商品广告信息
    @Override
    public goodsAd queryById(Integer id) {
        goodsAd goodsAd = goodsAdMapper.selectByPrimaryKey(id);
        return goodsAd;
    }

    //保存修改商品广告信息
    @Override
    public void queryByUpdate(goodsAd goodsAd) {
        goodsAdMapper.updateByPrimaryKeySelective(goodsAd);
    }
}
