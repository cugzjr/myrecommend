package com.xxxx.server.dao;

import com.xxxx.server.relation.SellRelation;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;

/**
 * 出售商品图数据库操作接口
 * @Author: 朱佳睿
 * @Time: 2023.03.26
 */
public interface SellRespository extends Neo4jRepository<SellRelation, Long> {
    /**
     * 删除出售
     * @param userId 用户
     * @param productId 商品
     */
    @Query("MATCH (u:User)-[r:出售]->(f:Product) WHERE u.userId = $userId AND f.productId = $productId DELETE r")
    void deleteBuyRelation(@Param("userId") Integer userId, @Param("productId") Integer productId);
}
