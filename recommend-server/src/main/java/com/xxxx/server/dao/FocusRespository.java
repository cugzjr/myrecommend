package com.xxxx.server.dao;

import com.xxxx.server.relation.FocusRelation;
import org.springframework.data.neo4j.repository.Neo4jRepository;

/**
 * 关注用户图数据库操作接口
 * @Author: 朱佳睿
 * @Time: 2023.03.26
 */
public interface FocusRespository extends Neo4jRepository<FocusRelation, Long> {
}
