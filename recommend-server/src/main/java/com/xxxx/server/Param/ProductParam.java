package com.xxxx.server.Param;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 商品接口参数实体类
 * @Author: 朱佳睿
 * @Time: 2023.03.29
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="BuyProductParam", description="")
public class ProductParam {
    private Integer userId;
    private Integer productId;
    private String name;
}
