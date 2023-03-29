package com.xxxx.server.service.impl;

import com.xxxx.server.dao.FocusRespository;
import com.xxxx.server.dao.UserRespository;
import com.xxxx.server.pojo.RespBean;
import com.xxxx.server.pojo.User;
import com.xxxx.server.relation.FocusRelation;
import com.xxxx.server.service.FocusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 关注用户接口实现
 * @Author: 朱佳睿
 * @Time: 2023.03.29
 */
@Service
public class FocusServiceImpl implements FocusService {
    @Autowired
    private UserRespository userRespository;
    @Autowired
    private FocusRespository focusRespository;

    /**
     * 关注用户
     * @param startId
     * @param endId
     * @return RespBean
     * @Author: 朱佳睿
     * @Time: 2023.03.29
     */
    @Override
    public RespBean focusUser(Integer startId, Integer endId)
    {
        User startUser = userRespository.findByUserId(startId);
        User endUser = userRespository.findByUserId(endId);
        FocusRelation focusRelation = FocusRelation.builder().startUser(startUser).endUser(endUser).relation("关注").build();
        focusRespository.save(focusRelation);
        return RespBean.success("关注成功");
    }
}
