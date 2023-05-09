package com.xxxx.server.service;

import com.xxxx.server.pojo.RespBean;

import java.util.List;

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

    /**
     * 取消收藏
     * @param userId
     * @param productId
     * @return RespBean
     * @Author: 朱佳睿
     * @Time: 2023.05.01
     */
    RespBean deleteCollect(Integer userId, Integer productId);
    RespBean checkCollect(Integer userId, Integer productId);
    RespBean getCollectNum(Integer productId);
    List<Integer> getMyCollect(Integer userId, Integer page);
}
