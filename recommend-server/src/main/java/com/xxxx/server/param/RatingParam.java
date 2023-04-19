package com.xxxx.server.param;

import io.swagger.annotations.ApiModel;
import io.swagger.models.auth.In;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 用户打分实体类
 * @Author: 朱佳睿
 * @Time: 2023.04.19
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="RatingParam", description="")
public class RatingParam {
    private Integer userId;

    private Integer productId;

    private Double score;
}
