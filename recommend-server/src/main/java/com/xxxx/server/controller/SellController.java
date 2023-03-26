package com.xxxx.server.controller;

import com.xxxx.server.Param.ProductParam;
import com.xxxx.server.pojo.RespBean;
import com.xxxx.server.service.SellService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sell")
public class SellController {
    @Autowired
    private SellService sellService;

    @ApiOperation(value = "出售物品")
    @PostMapping("/sellproduuct")
    public RespBean sellProduct(@RequestBody ProductParam productParam)
    {
        return sellService.sellProduct(productParam);
    }
}
