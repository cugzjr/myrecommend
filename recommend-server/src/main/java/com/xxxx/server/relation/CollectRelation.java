package com.xxxx.server.relation;

import com.xxxx.server.pojo.Product;
import com.xxxx.server.pojo.User;
import lombok.Builder;
import lombok.Data;
import org.neo4j.ogm.annotation.*;

@Data
@RelationshipEntity(type = "收藏")
@Builder
public class CollectRelation {
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
