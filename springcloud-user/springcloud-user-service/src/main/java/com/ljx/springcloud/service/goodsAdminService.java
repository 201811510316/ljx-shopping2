package com.ljx.springcloud.service;

import com.ljx.springcloud.pojo.goodsAdmin;

public interface goodsAdminService {
    //管理员登录
    goodsAdmin loginByAdmin(String username, String password);
}
