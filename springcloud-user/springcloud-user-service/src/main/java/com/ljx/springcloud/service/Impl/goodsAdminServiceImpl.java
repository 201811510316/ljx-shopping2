package com.ljx.springcloud.service.Impl;

import com.ljx.springcloud.mapper.goodsAdminMapper;
import com.ljx.springcloud.pojo.goodsAdmin;
import com.ljx.springcloud.service.goodsAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

@Service
public class goodsAdminServiceImpl implements goodsAdminService {

    @Autowired
    goodsAdminMapper goodsAdminMapper;

    //登录
    @Override
    public goodsAdmin loginByAdmin(String username, String password) {
        Example example = new Example(goodsAdmin.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("username",username).andEqualTo("password",password);

        goodsAdmin goodsAdmin = goodsAdminMapper.selectOneByExample(example);
        if(goodsAdmin!=null){
            return goodsAdmin;
        }
        return null;
    }
}
