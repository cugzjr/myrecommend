package com.xxxx.server.relation;

import com.xxxx.server.pojo.User;
import lombok.Builder;
import lombok.Data;
import org.neo4j.ogm.annotation.*;

/**
 * 关注关系
 * @Author: 朱佳睿
 * @Time: 2023.03.29
 */
@Data
@RelationshipEntity(type = "关注")
@Builder
public class FocusRelation {
    @Id
    @GeneratedValue
    private Long id;
    @StartNode
    private User startUser;     //关注者
    @EndNode
    private User endUser;       //被关注者
    @Property
    private String relation;   //关系名
}
