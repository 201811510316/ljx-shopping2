package com.ljx.springcloud.serivce.Impl;

import com.ljx.springcloud.client.UserClient;
import com.ljx.springcloud.config.JwtProperties;
import com.ljx.springcloud.entiy.UserInfo;
import com.ljx.springcloud.pojo.goodsAdmin;
import com.ljx.springcloud.serivce.AuthService;
import com.ljx.springcloud.utils.JwtUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@EnableConfigurationProperties(JwtProperties.class)
public class AuthServiceImpl implements AuthService {

    @Autowired
    JwtProperties jwtProperties;

    @Autowired
    UserClient userClient;

    private static Logger logger = LoggerFactory.getLogger(AuthServiceImpl.class);

    //查询用户
    @Override
    public String authentication(String username, String password) {
        //查询登录用户
        goodsAdmin login = this.userClient.login(username, password);
        if(login==null){
            throw new RuntimeException("用户不存在");
        }
        try {
            String token = JwtUtils.generateToken(
                    new UserInfo(login.getId(),login.getUsername()),
                    jwtProperties.getPrivateKey(),jwtProperties.getExpire());
            return token;
        } catch (Exception e) {
            return null;
        }
    }
}
