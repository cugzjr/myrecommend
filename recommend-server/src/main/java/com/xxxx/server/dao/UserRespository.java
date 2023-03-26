package com.xxxx.server.dao;

import com.xxxx.server.pojo.User;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;

public interface UserRespository extends Neo4jRepository<User, Long> {

    User findByUserId(Integer userId);

    @Query("MATCH (n) WHERE id(n) = :#{#user.Id} SET n.userId = :#{#user.userId},n.name = :#{#user.name} RETURN n")
    User updateByNode(@Param("user") User user);
}
