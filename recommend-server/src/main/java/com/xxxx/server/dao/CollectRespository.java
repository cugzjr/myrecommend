package com.xxxx.server.dao;

import com.xxxx.server.relation.CollectRelation;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;

/**
 * 收藏商品图数据库操作接口
 * @Author: 朱佳睿
 * @Time: 2023.03.26
 */
public interface CollectRespository extends Neo4jRepository<CollectRelation, Long> {
    /**
     * 删除收藏
     * @param userId 用户
     * @param productId 商品
     */
    @Query("MATCH (u:User)-[r:收藏]->(f:Product) WHERE u.userId = $userId AND f.productId = $productId DELETE r")
    void deleteCollectRelation(@Param("userId") Integer userId, @Param("productId") Integer productId);
}
