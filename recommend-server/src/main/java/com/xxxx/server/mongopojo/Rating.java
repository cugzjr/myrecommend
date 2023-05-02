package com.xxxx.server.mongopojo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Rating  {

    private String _id;
    private int userId;
    private int productId;
    private double score;
    private int timestamp;
}
