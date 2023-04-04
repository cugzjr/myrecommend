package com.xxxx.server.dao;

import com.xxxx.server.pojo.User;
import com.xxxx.server.relation.FocusRelation;
import io.swagger.models.auth.In;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Map;

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

    /**
     * 推荐感兴趣的人
     * @param userId
     * @param limit
     * @return
     * @Author: 朱佳睿
     * @Time: 2023.04.04
     */
    @Query("MATCH (me:User {userId: $userId})\n" +
            "WITH me\n" +
            "OPTIONAL MATCH (me)-[:关注]->(following)\n" +
            "WITH collect(following) as myFollowing, me\n" +
            "MATCH (other:User)\n" +
            "WHERE other.userId <> $userId\n" +
            "WITH other, reduce(s = 0, f in myFollowing | \n" +
            "    CASE WHEN (other)-[:关注]->(f) THEN s + 1 ELSE s END\n" +
            ") as similarity, size((other)<-[:关注]-()) as followers\n" +
            "WHERE NOT (me)-[:关注]->(other)\n" +
            "RETURN other, similarity, followers, similarity * 0.9 + followers * 0.1 as weightedScore\n" +
            "ORDER BY weightedScore DESC LIMIT $limit")
    List<User> recommendUsers(@Param("userId") Integer userId, @Param("limit") Integer limit);

//    @Query("MATCH (me:User {userId: $userId})\n" +
//            "WITH me\n" +
//            "OPTIONAL MATCH (me)-[:关注]->(following)\n" +
//            "WITH collect(following) as myFollowing, me\n" +
//            "MATCH (other:User)\n" +
//            "WHERE other.userId <> $userId\n" +
//            "WITH other, reduce(s = 0, f in myFollowing | \n" +
//            "    CASE WHEN (other)-[:关注]->(f) THEN s + 1 ELSE s END\n" +
//            ") as similarity, size((other)<-[:关注]-()) as followers\n" +
//            "WHERE NOT (me)-[:关注]->(other)\n" +
//            "RETURN other, similarity, followers, similarity * 0.9 + followers * 0.1 as weightedScore\n" +
//            "ORDER BY weightedScore DESC LIMIT $limit")
//    List<Map<String, Object>> recommend(@Param("userId") Integer userId, @Param("limit") Integer limit);
//
//    @Query("MATCH (u1:User {userId: $userId1})-[r1:关注]->(u2:User)<-[r2:关注]-(u3:User {userId: $userId2})\n" +
//            "WITH count(r1) as u1u2, count(r2) as u2u3\n" +
//            "RETURN u1u2 + u2u3 as similarity")
//    Integer calculateSimilarity(@Param("userId1") Integer userId1, @Param("userId2") Integer userId2);

}
