package com.xxxx.server.relation;

import com.xxxx.server.pojo.Product;
import com.xxxx.server.pojo.User;
import lombok.Builder;
import lombok.Data;
import org.neo4j.ogm.annotation.*;

/**
 * 购买关系
 * @Author: 朱佳睿
 * @Time: 2023.03.29
 */
@Data
@RelationshipEntity(type = "购买")
@Builder
public class BuyRelation {
    @Id
    @GeneratedValue
    private Long id;

    @StartNode
    private User user;
    @EndNode
    private Product product;
    @Property
    private String relation;
}
