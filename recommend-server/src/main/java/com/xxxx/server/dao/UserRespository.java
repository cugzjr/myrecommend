package com.xxxx.server.dao;

import com.xxxx.server.pojo.User;
import com.xxxx.server.relation.FocusRelation;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRespository extends Neo4jRepository<User, Long> {

    User findByUserId(Integer userId);

    @Query("MATCH (n) WHERE id(n) = :#{#user.Id} SET n.userId = :#{#user.userId},n.name = :#{#user.name} RETURN n")
    User updateByNode(@Param("user") User user);

    //获取我的关注
    @Query("MATCH (u:User {userId:{0}})-[:关注]->(f:User) RETURN f")
    List<User> getAllFollowings(Integer userId);

    //获取我的粉丝
    @Query("MATCH (u:User)-[r:关注]->(f:User {userId:{0}}) RETURN u")
    List<User> getAllFollowers(Integer userId);
}
