package com.ljx.springcloud.serivce;

public interface AuthService {

    //查询用户
    String authentication(String username,String password);
}
