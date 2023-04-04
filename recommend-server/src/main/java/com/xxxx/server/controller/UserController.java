package com.xxxx.server.controller;

import com.xxxx.server.pojo.RespBean;
import com.xxxx.server.pojo.User;
import com.xxxx.server.service.UserService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import scala.Int;

import java.util.ArrayList;
import java.util.List;

/**
 * 用户个人信息接口类
 * @Author: 朱佳睿
 * @Time: 2023.03.29
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    /**
     * 添加用户接口（注册）
     * @param userId
     * @param name
     * @return RespBean
     * @Time: 2023.03.29
     */
    @ApiOperation(value = "添加用户")
    @PostMapping("/adduser")
    public RespBean register(@RequestParam("userId") Integer userId, @RequestParam("name") String name)
    {
        return userService.register(userId, name);
    }

    /**
     * 修改用户姓名接口
     * @param userId
     * @param name
     * @return RespBean
     * @Time: 2023.03.29
     */
    @ApiOperation(value = "修改用户姓名")
    @PostMapping("/changeusername")
    public RespBean changeName(@RequestParam("userId") Integer userId, @RequestParam("name") String name)
    {
        return userService.changeName(userId, name);
    }

    /**
     * 推荐感兴趣的人
     * @param userId
     * @return List<Integer>
     */
    @ApiOperation(value = "推荐感兴趣的人")
    @GetMapping("/recommenduser")
    public RespBean recommenduser(@RequestParam("userId") Integer userId){
        return RespBean.success("响应成功", userService.recommendUsers(userId));
    }
}
