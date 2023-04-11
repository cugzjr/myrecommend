package com.xxxx.server.dao;

import com.xxxx.server.relation.BuyRelation;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

/**
 * 购买商品图数据库操作接口
 * @Author: 朱佳睿
 * @Time: 2023.03.26
 */
@Component
public interface BuyRespository extends Neo4jRepository<BuyRelation, Long> {

    /**
     * 创建购买关系
     * @param userId
     * @param productId
     * @Author: 朱佳睿
     * @Time: 2023.04.11
     */
    @Query("match (n:User),(m:Product) where n.userId=$userId and m.productId=$productId create (n)-[r:Buy]->(m)")
    void createRelation(@Param("userId") Integer userId, @Param("productId") Integer productId);
}
