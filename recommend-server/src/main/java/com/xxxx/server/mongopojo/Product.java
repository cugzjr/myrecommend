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
public class Product  {
    private String _id;
    private int productId;
    private String name;
    private String categories;
}
