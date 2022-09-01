package com.ljx.springcloud.api;

import com.ljx.springcloud.pojo.goodsAdmin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

public interface UserApi {
    //根据用户名和密码查询用户
    @PostMapping("/admin")
    goodsAdmin login(@RequestParam("username")String username, @RequestParam("password")String password);
}
