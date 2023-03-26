package com.xxxx.server.dao;

import com.xxxx.server.relation.FocusRelation;
import org.springframework.data.neo4j.repository.Neo4jRepository;

public interface FocusRespository extends Neo4jRepository<FocusRelation, Long> {
}
