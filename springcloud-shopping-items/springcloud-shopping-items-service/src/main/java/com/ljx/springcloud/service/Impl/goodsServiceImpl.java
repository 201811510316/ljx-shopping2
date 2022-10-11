package com.ljx.springcloud.service.Impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.ljx.springcloud.mapper.*;
import com.ljx.springcloud.pojo.goods;
import com.ljx.springcloud.pojo.goodsClassification;
import com.ljx.springcloud.service.goodsService;
import com.ljx.springcloud.utils.PageResult;
import com.ljx.springcloud.pojo.goodsFenLei;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class goodsServiceImpl implements goodsService {

    @Autowired
    goodsMapper goodsMapper;

    @Autowired
    goodsImgMapper goodsImgMapper;

    @Autowired
    goodsSlideMapper goodsSlideMapper;

    @Autowired
    goodsRemarkMapper goodsRemarkMapper;

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

       return new PageResult<>(pageInfo.getTotal(),pageInfo.getPages(),goodsFenLeis);
    }

    //保存添加商品
    @Override
    public Integer save(goods goods) {
        goods goods1 = new goods();
        goods1.setGoodsName(goods.getGoodsName());
        goods1.setGoodsPrice(goods.getGoodsPrice());
        goods1.setGoodsSales(0);
        goods1.setGoodsStock(goods.getGoodsStock());
        goods1.setDetail(goods.getDetail());
        goods1.setCategoryId(goods.getCategoryId());
        goods1.setDefaultSize(goods.getDefaultSize());
        goods1.setState(1);
        goods1.setGoodsHot(0);
        goods1.setGoodsNew(1);
        int insert = goodsMapper.insert(goods1);
        return insert;
    }

//    //查询单个商品信息（用于修改回显）
//    @Override
//    public goodsFenLei queryGoodsById(Integer id) {
//        goods goods = goodsMapper.selectByPrimaryKey(id);
//        goodsFenLei goodsFenLei = new goodsFenLei();
//        //把goods转变为goodsFenLei -- 属性拷贝
//        BeanUtils.copyProperties(goods, goodsFenLei);
//        goodsClassification key = goodsClassificationMapper.selectByPrimaryKey(goods.getCategoryId());
//        goodsFenLei.setCategoryName(key.getCategoryName());
//        return goodsFenLei;
//    }

    //查询单个商品信息（用于修改回显）
    @Override
    public goods queryGoodsById(Integer id) {
        return goodsMapper.selectByPrimaryKey(id);
    }

    //保存修改商品信息
    @Override
    public void update(goods goods) {
        goodsMapper.updateByPrimaryKeySelective(goods);
    }

    //删除商品
    @Transactional(rollbackFor = Exception.class)
    @Override
    public Integer deleteByGoods(Integer id) {
        //创建查询条件
        Example example = new Example(goods.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("goodsId",id);

        int i = goodsMapper.deleteByExample(example);
        if(i>0){
            goodsImgMapper.deleteByExample(example);
            goodsRemarkMapper.deleteByExample(example);
            goodsSlideMapper.deleteByExample(example);
            return 1;
        }
        return null;
    }


}
