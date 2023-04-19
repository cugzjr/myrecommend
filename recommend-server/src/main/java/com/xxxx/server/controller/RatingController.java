package com.xxxx.server.controller;

import com.xxxx.server.param.RatingParam;
import com.xxxx.server.pojo.RespBean;
import io.swagger.annotations.ApiOperation;
import io.swagger.models.auth.In;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.*;

/**
 * 评分接口类
 * @Author: 朱佳睿
 * @Time: 2023.03.26
 */
@RestController
@RequestMapping("/rating")
public class RatingController {
    private static Logger logger = Logger.getLogger(RatingController.class.getName());
    @ApiOperation(value = "商品打分")
    @PostMapping("/productscore")
    public RespBean productRate(@RequestBody RatingParam ratingParam){
        logger.info("PRODUCT_RATING_PREFIX" + ":" + ratingParam.getUserId() + "|" + ratingParam.getProductId() + "|"
                + ratingParam.getScore() + "|" + System.currentTimeMillis() / 1000);
        return RespBean.success("评分成功");
    }
}
