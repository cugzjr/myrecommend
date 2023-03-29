package com.xxxx.server.dao;

import com.xxxx.server.relation.SellRelation;
import org.springframework.data.neo4j.repository.Neo4jRepository;

/**
 * 出售商品图数据库操作接口
 * @Author: 朱佳睿
 * @Time: 2023.03.26
 */
public interface SellRespository extends Neo4jRepository<SellRelation, Long> {
}
