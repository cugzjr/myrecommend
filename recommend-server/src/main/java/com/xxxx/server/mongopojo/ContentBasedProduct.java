package com.xxxx.server.mongopojo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * @author 朱佳睿
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class ContentBasedProduct {
    private String _id;
    private Integer productId;
    private List<ProductScore> recs;
}
