package com.ljx.springcloud.service.Impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.ljx.springcloud.mapper.goodsMapper;
import com.ljx.springcloud.mapper.goodsRemarkMapper;
import com.ljx.springcloud.pojo.goods;
import com.ljx.springcloud.pojo.goodsRemark;
import com.ljx.springcloud.service.goodsRemarkService;
import com.ljx.springcloud.utils.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service
public class goodsRemarkServiceImpl implements goodsRemarkService {

    @Autowired
    goodsRemarkMapper goodsRemarkMapper;

    @Autowired
    goodsMapper goodsMapper;

    //查看
    @Override
    public PageResult<goodsRemark> findByGoodsRemark(Integer page, Integer rows) {
        PageHelper.startPage(page,rows);
        Page<goodsRemark> goodsRemarks = (Page<goodsRemark>) goodsRemarkMapper.selectAll();
        List<goodsRemark> result = goodsRemarks.getResult();
        return new PageResult<>(goodsRemarks.getTotal(),goodsRemarks.getPages(),result);
    }
    //添加
    @Override
    public Integer findByAddRemark(goodsRemark goodsRemark) {
        Example example = new Example(goodsRemark.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("goodsId",goodsRemark.getGoodsId());
        goods goods = goodsMapper.selectOneByExample(example);
        if(goods!=null){
            return goodsRemarkMapper.insert(goodsRemark);
        }
        return null;
    }
    //查看单个商品的评价
    @Override
    public goodsRemark findBySelectId(Integer id) {
        Example example = new Example(goodsRemark.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("goodsId",id);
        goodsRemark goodsRemark = goodsRemarkMapper.selectOneByExample(example);
        return goodsRemark;
    }
    //修改
    @Override
    public Integer findByUpdateRemark(goodsRemark goodsRemark) {
        return goodsRemarkMapper.updateByPrimaryKey(goodsRemark);
    }
    //删除
    @Override
    public Integer findDeleteRemark(Integer id) {
        Example example = new Example(goodsRemark.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("goodsId",id);
        int i = goodsRemarkMapper.deleteByExample(example);
        return i;
    }
}
