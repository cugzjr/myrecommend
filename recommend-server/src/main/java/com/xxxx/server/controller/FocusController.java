package com.xxxx.server.controller;

import com.xxxx.server.pojo.RespBean;
import com.xxxx.server.service.FocusService;
import com.xxxx.server.service.UserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/focus")
public class FocusController {
    @Autowired
    private FocusService focusService;
    @Autowired
    private UserService userService;

    @ApiOperation(value = "关注用户")
    @PostMapping("/focususer")
    public RespBean focusUser(@RequestParam("startId") Integer startId, @RequestParam("endId") Integer endId)
    {
        return focusService.focusUser(startId, endId);
    }

    @ApiOperation(value = "我的关注")
    @PostMapping("/myfocus")
    public List<Integer> getFocus(@RequestParam("userId") Integer userId){
        return userService.getAllFollwings(userId);
    }

    @ApiOperation(value = "我的关注数")
    @PostMapping("/myfocusnum")
    public Integer getFocusnum(@RequestParam("userId") Integer userId){
        List<Integer> res = userService.getAllFollwings(userId);
        if(res == null) return 0;
        else return res.size();
    }

    @ApiOperation(value = "我的粉丝")
    @PostMapping("/myfans")
    public List<Integer> getFans(@RequestParam("userId") Integer userId){
        return userService.getAllFollwers(userId);
    }

    @ApiOperation(value = "我的粉丝数")
    @PostMapping("/myfansnum")
    public Integer getFansnum(@RequestParam("userId") Integer userId){
        List<Integer> res = userService.getAllFollwers(userId);
        if(res == null) return 0;
        else return res.size();
    }
}
