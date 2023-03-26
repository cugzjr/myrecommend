package com.xxxx.server.service;

import com.xxxx.server.pojo.RespBean;

public interface UserService {

    RespBean register(Integer userId, String name);  //注册
    RespBean changeName(Integer userId, String name);  //修改用户姓名
}
