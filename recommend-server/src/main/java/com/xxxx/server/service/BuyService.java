package com.xxxx.server.service;

import com.xxxx.server.pojo.RespBean;

/**
 * 购买相关操作接口
 * @Author: 朱佳睿
 * @Time: 2023.03.29
 */
public interface BuyService {
    RespBean buyProduct(Integer userId, Integer productId);
}
