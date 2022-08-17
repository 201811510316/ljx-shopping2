package com.ljx.springcloud.service.Impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.ljx.springcloud.mapper.goodsClassificationMapper;
import com.ljx.springcloud.mapper.goodsMapper;
import com.ljx.springcloud.pojo.goods;
import com.ljx.springcloud.pojo.goodsClassification;
import com.ljx.springcloud.service.goodsService;
import com.ljx.springcloud.utils.PageResult;
import com.ljx.springcloud.pojo.goodsFenLei;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class goodsServiceImpl implements goodsService {

    @Autowired
    goodsMapper goodsMapper;

    @Autowired
    goodsClassificationMapper goodsClassificationMapper;

    //以分页方式展示商品信息
    @Override
    public PageResult<goodsFenLei> queryByGoods(Integer page, Integer rows, Integer saleable) {
        //开始分页
        PageHelper.startPage(page,Math.min(rows,200));
        //创建查询条件
        Example example = new Example(goods.class);
        Example.Criteria criteria = example.createCriteria();
        //是否过滤上下架
        if(saleable!=null){
            criteria.orEqualTo("state",saleable);
        }
        Page<goods> pageInfo = (Page<goods>) goodsMapper.selectByExample(example);

        List<goodsFenLei> goodsFenLeis = pageInfo.getResult().stream().map(goods -> {
            goodsFenLei goodsFenLei = new goodsFenLei();
            //把goods转变为goodsFenLei -- 属性拷贝
            BeanUtils.copyProperties(goods, goodsFenLei);
            goodsClassification key = goodsClassificationMapper.selectByPrimaryKey(goods.getCategoryId());
            goodsFenLei.setCategoryName(key.getCategoryName());
            return goodsFenLei;
        }).collect(Collectors.toList());

       return new PageResult<>(pageInfo.getTotal(),goodsFenLeis);
    }

    //保存添加商品
    @Override
    public void save(goodsFenLei goodsFenLei) {
        goodsFenLei.setGoodsName(goodsFenLei.getGoodsName());
        goodsFenLei.setGoodsPrice(goodsFenLei.getGoodsPrice());
        goodsFenLei.setGoodsStock(goodsFenLei.getGoodsStock());
        goodsFenLei.setDetail(goodsFenLei.getDetail());
        goodsFenLei.setCategoryId(goodsFenLei.getCategoryId());
        goodsFenLei.setState(1);
        goodsMapper.insert(goodsFenLei);
    }

    //查询单个商品信息（用于修改回显）
    @Override
    public goodsFenLei queryGoodsById(Integer id) {
        goods goods = goodsMapper.selectByPrimaryKey(id);
        goodsFenLei goodsFenLei = new goodsFenLei();
        //把goods转变为goodsFenLei -- 属性拷贝
        BeanUtils.copyProperties(goods, goodsFenLei);
        goodsClassification key = goodsClassificationMapper.selectByPrimaryKey(goods.getCategoryId());
        goodsFenLei.setCategoryName(key.getCategoryName());
        return goodsFenLei;
    }

    //保存修改商品信息
    @Override
    public void update(goods goods) {
        goodsMapper.updateByPrimaryKeySelective(goods);
    }

    //删除商品
    @Override
    public void deleteByGoods(Integer id) {
        //创建查询条件
        Example example = new Example(goods.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("goodsId",id);
        goodsMapper.deleteByExample(example);
    }


}
