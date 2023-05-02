package com.xxxx.server.controller;

import com.xxxx.server.pojo.RespBean;
import com.xxxx.server.service.CollectService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 收藏接口类
 * @Author: 朱佳睿
 * @Time: 2023.03.26
 */
@RestController
@RequestMapping("/collect")
public class CollectController {
    @Autowired
    private CollectService collectService;

    /**
     * 收藏商品接口
     * @param userId 用户Id
     * @param productId
     * @return: RespBean
     * @Author: 朱佳睿
     * @Time: 2023.03.26
     */
    @ApiOperation(value = "收藏商品")
    @PostMapping("/collectproduct")
    public RespBean collectProduct(@RequestParam("userId") Integer userId, @RequestParam("productId") Integer productId)
    {
        return collectService.collectProduct(userId, productId);
    }

    @ApiOperation(value = "删除收藏")
    @DeleteMapping
    public RespBean deleteCollect(@RequestParam("userId") Integer userId, @RequestParam("productId") Integer productId)
    {
        return collectService.deleteCollect(userId, productId);
    }
}
