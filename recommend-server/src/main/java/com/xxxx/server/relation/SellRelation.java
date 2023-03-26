package com.xxxx.server.relation;

import com.xxxx.server.pojo.Product;
import com.xxxx.server.pojo.User;
import lombok.Builder;
import lombok.Data;
import org.neo4j.ogm.annotation.*;

@Data
@RelationshipEntity(type = "出售")
@Builder
public class SellRelation {
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
