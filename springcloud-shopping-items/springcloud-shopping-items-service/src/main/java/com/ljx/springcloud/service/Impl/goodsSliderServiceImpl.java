package com.ljx.springcloud.service.Impl;

import com.ljx.springcloud.mapper.goodsSlideMapper;
import com.ljx.springcloud.pojo.goodsSlide;
import com.ljx.springcloud.service.goodsSliderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service
public class goodsSliderServiceImpl implements goodsSliderService {
    @Autowired
    goodsSlideMapper goodsSlideMapper;

    //查看单个商品轮播图信息
    @Override
    public List<goodsSlide> queryBySelectSlide(Integer id) {
        Example example = new Example(goodsSlide.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("goodsId",id);
        List<goodsSlide> goodsSlides = goodsSlideMapper.selectByExample(example);
        return goodsSlides;
    }
}
