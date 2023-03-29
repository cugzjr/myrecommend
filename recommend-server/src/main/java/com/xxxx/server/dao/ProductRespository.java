package com.xxxx.server.dao;

import com.xxxx.server.pojo.Product;
import org.springframework.data.neo4j.repository.Neo4jRepository;

/**
 * 商品信息图数据库操作接口
 * @Author: 朱佳睿
 * @Time: 2023.03.26
 */
public interface ProductRespository extends Neo4jRepository<Product, Long> {
    Product findByProductId(Integer productId);
}
