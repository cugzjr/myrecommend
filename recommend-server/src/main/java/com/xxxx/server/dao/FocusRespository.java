package com.xxxx.server.dao;

import com.xxxx.server.relation.FocusRelation;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;

/**
 * 关注用户图数据库操作接口
 * @Author: 朱佳睿
 * @Time: 2023.03.26
 */
public interface FocusRespository extends Neo4jRepository<FocusRelation, Long> {
    @Query("MATCH (u:User)-[:关注]->() RETURN COUNT(DISTINCT u)")
    int getAllPeopleFocus();    //获取所有关注过他人的用户总数

    @Query("MATCH ()-[:关注]->(u:User) RETURN COUNT(DISTINCT u)")
    int getAllPeopleFellows();    //获取所有关注过他人的用户总数
}
