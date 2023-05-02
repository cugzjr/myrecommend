package com.xxxx.server.controller;

import com.xxxx.server.param.ProductParam;
import com.xxxx.server.pojo.RespBean;
import com.xxxx.server.service.SellService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 出售商品类
 * @Author: 朱佳睿
 * @Time: 2023.03.29
 */
@RestController
@RequestMapping("/sell")
public class SellController {
    @Autowired
    private SellService sellService;

    /**
     * 出售物品接口
     * @param productParam
     * @return RespBean
     * @Time: 2023.03.29
     */
    @ApiOperation(value = "出售物品")
    @PostMapping("/sellproduuct")
    public RespBean sellProduct(@RequestBody ProductParam productParam)
    {
        return sellService.sellProduct(productParam);
    }

    @ApiOperation(value = "删除发布")
    @DeleteMapping
    public RespBean deleteCollect(@RequestParam("userId") Integer userId, @RequestParam("productId") Integer productId)
    {
        return sellService.deleteCollect(userId, productId);
    }
}
