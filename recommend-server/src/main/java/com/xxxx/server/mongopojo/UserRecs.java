package com.xxxx.server.mongopojo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class UserRecs {
    private String _id;
    private Integer userId;
    private List<ProductScore> recs;
}
