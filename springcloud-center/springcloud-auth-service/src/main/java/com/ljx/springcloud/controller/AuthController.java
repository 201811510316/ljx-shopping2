package com.ljx.springcloud.controller;

import com.ljx.springcloud.config.JwtProperties;
import com.ljx.springcloud.entiy.UserInfo;
import com.ljx.springcloud.serivce.AuthService;
import com.ljx.springcloud.utils.JwtUtils;
import com.ljx.springcloud.utils.cookieUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@EnableConfigurationProperties(JwtProperties.class)
public class AuthController {
    @Autowired
    AuthService authService;

    @Autowired
    JwtProperties jwtProperties;

    //登录授权
    @PostMapping("/accredit")
    public ResponseEntity<Void> aunthentication(@RequestParam("username")String username, @RequestParam("password")String password, HttpServletRequest request, HttpServletResponse response){
        String token = authService.authentication(username, password);
        if(StringUtils.isBlank(token)){
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        cookieUtils.setCookie(request,response,jwtProperties.getCookieName(),token,jwtProperties.getCookieMaxAge(),null,true);
        return ResponseEntity.ok().build();
    }

    //验证用户信息
    @GetMapping("/verify")
    public ResponseEntity<UserInfo> verifyUser(@CookieValue("LY_TOKEN")String token,HttpServletRequest request,HttpServletResponse response){
        try {
            //获取token信息
            UserInfo userInfo = JwtUtils.getInfoFromToken(token, jwtProperties.getPublicKey());
            //刷新token
            String newToken = JwtUtils.generateToken(userInfo, jwtProperties.getPrivateKey(), jwtProperties.getExpire());
            //然后加入cookie，并指定httpOnly为true,防止通过JS获取和修改
            cookieUtils.setCookie(request,response,jwtProperties.getCookieName(),newToken,jwtProperties.getCookieMaxAge(),null,true);
            return ResponseEntity.ok(userInfo);
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }
}