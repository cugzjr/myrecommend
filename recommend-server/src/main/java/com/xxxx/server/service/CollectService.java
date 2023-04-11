package com.xxxx.server.service;

import com.xxxx.server.pojo.RespBean;

/**
 * 收藏相关操作接口
 * @Author: 朱佳睿
 * @Time: 2023.03.29
 */
public interface CollectService {
    /**
     * 收藏商品
     * @param userId
     * @param productId
     * @return RespBean
     * @Author: 朱佳睿
     * @Time: 2023.04.11
     */
    RespBean collectProduct(Integer userId, Integer productId);
}
