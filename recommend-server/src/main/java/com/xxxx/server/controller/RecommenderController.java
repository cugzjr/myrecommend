package com.xxxx.server.controller;

import com.xxxx.server.mongopojo.RateMoreRecentlyProduct;
import com.xxxx.server.pojo.RespBean;
import com.xxxx.server.service.RecommendService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/recommend")
public class RecommenderController {
    @Autowired
    private RecommendService recommendService;

    /**
     * 首页推荐接口
     * @param userId 用户Id
     * @param page   页码
     * @return 推荐结果
     */
    @ApiOperation(value = "首页推荐")
    @GetMapping("/homepage")
    RespBean homeRecommend(@RequestParam(value = "userId") Integer userId, @RequestParam(value = "page") Integer page)
    {
        // 检查用户是否是新人用户
        if(!recommendService.checkUserNew(userId)){
            // 如果是新人用户，推荐热门商品
            System.out.print(1);
            return RespBean.success("热门推荐成功",recommendService.getHotProducts(page));
        }
        // 若用户有评分记录，则基于ASL模型为用户进行离线推荐
        else{
            return RespBean.success("离线推荐成功",recommendService.getUserRecsProducts(userId,page));
        }
    }

    @ApiOperation(value="商品页推荐")
    @GetMapping("/productpage")
    RespBean detailRecommend(@RequestParam(value = "productId") Integer productId, @RequestParam(value = "page") Integer page)
    {
        return RespBean.success("商品详情页推荐成功",recommendService.detailRecommend(productId,page));
    }
}
