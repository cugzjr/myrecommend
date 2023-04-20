package com.xxxx.server.mongopojo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * @author 朱佳睿
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class ProductScore {
   private Integer productId;
   private Double score;
}
