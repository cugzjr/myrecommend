package com.xxxx.server.dao;

import com.xxxx.server.pojo.User;
import com.xxxx.server.relation.FocusRelation;
import io.swagger.models.auth.In;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;

/**
 * 关注用户图数据库操作接口
 * @Author: 朱佳睿
 * @Time: 2023.03.26
 */
public interface FocusRespository extends Neo4jRepository<FocusRelation, Long> {
    /**
     * 获取所有关注过他人的用户总数
     * @return 关注过他人的用户总数
     * @Author: 朱佳睿
     * @Time: 2023.04.11
     */
    @Query("MATCH (u:User)-[:关注]->() RETURN COUNT(DISTINCT u)")
    int getAllPeopleFocus();

    /**
     * 获取所有关注过他人的用户总数
     * @return 所有关注过他人的用户总数
     * @Author: 朱佳睿
     * @Time: 2023.04.11
     */
    @Query("MATCH ()-[:关注]->(u:User) RETURN COUNT(DISTINCT u)")
    int getAllPeopleFellows();

    /**
     * 删除关注关系
     * @param startId
     * @param endId
     */
    @Query("MATCH (u:User)-[r:关注]->(f:User) WHERE u.userId = $startId AND f.userId = $endId DELETE r")
    void deleteFocusRelation(@Param("startId") Integer startId, @Param("endId") Integer endId);
}
