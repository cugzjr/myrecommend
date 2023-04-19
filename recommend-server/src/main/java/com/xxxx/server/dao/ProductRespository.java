package com.xxxx.server.dao;

import com.xxxx.server.pojo.Product;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;

/**
 * 商品信息图数据库操作接口
 * @Author: 朱佳睿
 * @Time: 2023.03.26
 */
public interface ProductRespository extends Neo4jRepository<Product, Long> {
    /**
     * 根据商品ID查找商品
     * @param productId
     * @return 商品
     * @Author: 朱佳睿
     * @Time: 2023.04.11
     */
    @Query("MATCH (n) WHERE n.productId = :#{#productId} return n")
    Product findByProductId(@Param("productId") Integer productId);
}
