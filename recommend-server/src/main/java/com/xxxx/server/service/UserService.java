package com.xxxx.server.service;

import com.xxxx.server.pojo.RespBean;
import io.swagger.models.auth.In;

import java.util.List;

public interface UserService {

    RespBean register(Integer userId, String name);  //注册
    RespBean changeName(Integer userId, String name);  //修改用户姓名

    List<Integer> getAllFollwings(Integer userId);  //获取我的关注

    List<Integer> getAllFollwers(Integer userId);  //获取我的粉丝
}
