package com.xxxx.server.controller;

import com.xxxx.server.pojo.RespBean;
import com.xxxx.server.service.UserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @ApiOperation(value = "添加用户")
    @PostMapping("/adduser")
    public RespBean register(@RequestParam("userId") Integer userId, @RequestParam("name") String name)
    {
        return userService.register(userId, name);
    }
    @ApiOperation(value = "修改用户姓名")
    @PostMapping("/changeusername")
    public RespBean changeName(@RequestParam("userId") Integer userId, @RequestParam("name") String name)
    {
        return userService.changeName(userId, name);
    }

}
