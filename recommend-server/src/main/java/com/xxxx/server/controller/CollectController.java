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

    @ApiOperation(value = "判断是否收藏")
    @GetMapping("checkcollect")
    public RespBean checkCollect(@RequestParam("userId") Integer userId, @RequestParam("productId") Integer productId)
    {
        return collectService.checkCollect(userId, productId);
    }
    @ApiOperation(value = "收藏总数")
    @GetMapping("collectnum")
    public RespBean getCollectNum(@RequestParam("productId") Integer productId){
        return collectService.getCollectNum(productId);
    }
    @ApiOperation(value = "我的收藏")
    @GetMapping("mycollect")
    public RespBean getMyCollect(@RequestParam("userId") Integer userId, @RequestParam("page") Integer page){
        return RespBean.success("获取成功", collectService.getMyCollect(userId, page));
    }
}
