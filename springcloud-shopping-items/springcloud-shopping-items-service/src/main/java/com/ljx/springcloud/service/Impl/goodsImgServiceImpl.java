package com.ljx.springcloud.service.Impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.ljx.springcloud.mapper.goodsImgMapper;
import com.ljx.springcloud.mapper.goodsMapper;
import com.ljx.springcloud.pojo.goods;
import com.ljx.springcloud.pojo.goodsImg;
import com.ljx.springcloud.pojo.goodsTupian;
import com.ljx.springcloud.service.goodsImgService;
import com.ljx.springcloud.utils.PageResult;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class goodsImgServiceImpl implements goodsImgService {

    @Autowired
    goodsImgMapper goodsImgMapper;

    @Autowired
    goodsMapper goodsMapper;

    //查询商品详情图片  -- 没必要
    @Override
    public PageResult<goodsTupian> qureyByGoodsTupian(Integer page, Integer rows) {
        //开始分页
        PageHelper.startPage(page,rows);
        Page<goodsImg> goodsImgs = (Page<goodsImg>) goodsImgMapper.selectAll();

        List<goodsTupian> goodsTupians = goodsImgs.getResult().stream().map(goodsImg -> {
            goodsTupian goodsTupian = new goodsTupian();
            //属性拷贝
            BeanUtils.copyProperties(goodsImg,goodsTupian);
            goods goods = goodsMapper.selectByPrimaryKey(goodsImg.getGoodsId());
            goodsTupian.setGoodsName(goods.getGoodsName());
            return goodsTupian;
        }).collect(Collectors.toList());

        return new PageResult<>(goodsImgs.getTotal(),goodsImgs.getPages(),goodsTupians);
    }

    //根据id查询对应商品详情图片
    @Override
    public goodsImg queryByGoodsImg(Integer id) {
        Example example = new Example(goodsImg.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("goodsId",id);
        goodsImg goodsImg = goodsImgMapper.selectOneByExample(example);
        return goodsImg;
    }

}
