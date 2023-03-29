package com.xxxx.server.pojo;

import lombok.Data;
import org.neo4j.ogm.annotation.*;

/**
 * 用户实体
 * @Author: 朱佳睿
 * @Time: 2023.03.29
 */
@Data
@NodeEntity(label = "User")
public class User {

    @Id
    @GeneratedValue
    private Long id;           //图数据库id
    @Property
    @Index(unique = true)
    private Integer userId;   //用户Id
    @Property
    private String name;      //用户名
}
