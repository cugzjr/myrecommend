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

    /**
     * 删除关注
     * @param startId
     * @param endId
     * @return 删除结果
     * @Author: 朱佳睿
     * @Time: 2023.04.12
     */
    RespBean deleteFocus(Integer startId, Integer endId);

    /**
     * 判断是否相互关注
     * @param startId 用户1
     * @param endId 用户2
     * @return 判断结果
     */
    RespBean checkBothFocus(Integer startId, Integer endId);
}
