package com.xxxx.server.dao;

import com.xxxx.server.relation.CollectRelation;
import org.springframework.data.neo4j.repository.Neo4jRepository;

/**
 * 收藏商品图数据库操作接口
 * @Author: 朱佳睿
 * @Time: 2023.03.26
 */
public interface CollectRespository extends Neo4jRepository<CollectRelation, Long> {
}
