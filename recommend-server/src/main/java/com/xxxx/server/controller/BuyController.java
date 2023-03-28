package com.xxxx.server.controller;

import com.xxxx.server.pojo.RespBean;
import com.xxxx.server.service.BuyService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 购买物品接口类
 * @Author:朱佳睿
 * @Time:2023.03.26
 */
@RestController
@RequestMapping("/buy")
public class BuyController {
    @Autowired
    private BuyService buyService;

    /**
     * 购买物品接口
     * @param userId
     * @param productId
     * @return:RespBean
     * @Author:朱佳睿
     * @Time:2023.03.26
     */
    @ApiOperation(value = "购买物品")
    @PostMapping("/buyproduuct")
    public RespBean buyProduct(@RequestParam("userId") Integer userId, @RequestParam("productId") Integer productId)
    {
        return buyService.buyProduct(userId, productId);
    }
}
