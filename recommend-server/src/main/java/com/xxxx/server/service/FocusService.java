package com.xxxx.server.service;

import com.xxxx.server.pojo.RespBean;

/**
 * 关注相关操作接口
 * @Author: 朱佳睿
 * @Time: 2023.03.29
 */
public interface FocusService {
    /**
     * 关注用户
     * @param startId
     * @param endId
     * @return 关注结果
     * @Author: 朱佳睿
     * @Time: 2023.04.11
     */
    RespBean focusUser(Integer startId, Integer endId);
}
