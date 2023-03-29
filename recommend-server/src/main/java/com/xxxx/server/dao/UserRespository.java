package com.xxxx.server.dao;

import com.xxxx.server.pojo.User;
import com.xxxx.server.relation.FocusRelation;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * 用户个人信息图数据库操作接口
 * @Author: 朱佳睿
 * @Time: 2023.03.26
 */
public interface UserRespository extends Neo4jRepository<User, Long> {

    User findByUserId(Integer userId);  //根据id查找用户

    @Query("MATCH (n) WHERE id(n) = :#{#user.Id} SET n.userId = :#{#user.userId},n.name = :#{#user.name} RETURN n")
    User updateByNode(@Param("user") User user);   //更新用户信息

    @Query("MATCH (u:User {userId:{0}})-[:关注]->(f:User) RETURN f")
    List<User> getAllFollowings(Integer userId);   //获取我的关注

    @Query("MATCH (u:User)-[r:关注]->(f:User {userId:{0}}) RETURN u")
    List<User> getAllFollowers(Integer userId);   //获取我的粉丝
}
