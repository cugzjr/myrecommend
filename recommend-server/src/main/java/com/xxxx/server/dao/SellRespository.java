package com.xxxx.server.dao;

import com.xxxx.server.relation.SellRelation;
import org.springframework.data.neo4j.repository.Neo4jRepository;

public interface SellRespository extends Neo4jRepository<SellRelation, Long> {
}
