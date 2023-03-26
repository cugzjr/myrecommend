package com.xxxx.server.pojo;

import lombok.Data;
import org.neo4j.ogm.annotation.*;

@Data
@NodeEntity(label = "User")
public class User {

    @Id
    @GeneratedValue
    private Long id;
    @Property
    @Index(unique = true)
    private Integer userId;   //用户Id
    @Property
    private String name;
}
