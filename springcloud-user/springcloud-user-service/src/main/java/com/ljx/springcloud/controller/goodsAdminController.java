package com.ljx.springcloud.controller;

import com.ljx.springcloud.pojo.goodsAdmin;
import com.ljx.springcloud.service.goodsAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
//登录检测
@RestController
public class goodsAdminController {

    @Autowired
    goodsAdminService goodsAdminService;

    //登录
    @PostMapping("/admin")
    public ResponseEntity<goodsAdmin> login(@RequestParam("username")String username,
                                            @RequestParam("password")String password){
        goodsAdmin goodsAdmin = goodsAdminService.loginByAdmin(username, password);
        if(goodsAdmin!=null){
            return ResponseEntity.ok(goodsAdmin);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
