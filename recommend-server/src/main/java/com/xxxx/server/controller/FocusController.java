package com.xxxx.server.controller;

import com.xxxx.server.pojo.RespBean;
import com.xxxx.server.service.FocusService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/focus")
public class FocusController {
    @Autowired
    private FocusService focusService;

    @ApiOperation(value = "关注用户")
    @PostMapping("/focususer")
    public RespBean focusUser(@RequestParam("startId") Integer startId, @RequestParam("endId") Integer endId)
    {
        return focusService.focusUser(startId, endId);
    }
}
