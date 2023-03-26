package com.xxxx.server.dao;

import com.xxxx.server.relation.CollectRelation;
import org.springframework.data.neo4j.repository.Neo4jRepository;

public interface CollectRespository extends Neo4jRepository<CollectRelation, Long> {
}
