package com.xxxx.server.dao;

import com.xxxx.server.pojo.Product;
import org.springframework.data.neo4j.repository.Neo4jRepository;

public interface ProductRespository extends Neo4jRepository<Product, Long> {
    Product findByProductId(Integer productId);
}
