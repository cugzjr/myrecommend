package com.xxxx.server.dao;

import com.xxxx.server.relation.BuyRelation;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

@Component
public interface BuyRespository extends Neo4jRepository<BuyRelation, Long> {

    @Query("match (n:User),(m:Product) where n.userId=$userId and m.productId=$productId create (n)-[r:Buy]->(m)")
    void createRelation(@Param("userId") Integer userId, @Param("productId") Integer productId);
}
