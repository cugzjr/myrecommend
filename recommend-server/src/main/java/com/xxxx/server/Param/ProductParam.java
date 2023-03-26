package com.xxxx.server.Param;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="BuyProductParam", description="")
public class ProductParam {
    private Integer userId;
    private Integer productId;
    private String name;
}
