package com.xxxx.server.controller;

import com.xxxx.server.pojo.RespBean;
import com.xxxx.server.service.CollectService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/collect")
public class CollectController {
    @Autowired
    private CollectService collectService;

    @ApiOperation(value = "收藏商品")
    @PostMapping("/collectproduct")
    public RespBean collectProduct(@RequestParam("userId") Integer userId, @RequestParam("productId") Integer productId)
    {
        return collectService.collectProduct(userId, productId);
    }
}
