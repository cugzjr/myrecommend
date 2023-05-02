package com.xxxx.server.service;

import com.xxxx.server.param.ProductParam;
import com.xxxx.server.pojo.RespBean;

/**
 * 出售商品相关操作接口
 * @Author: 朱佳睿
 * @Time: 2023.03.29
 */
public interface SellService {
    /**
     * 出售商品
     * @param productParam
     * @return 出售结果
     * @Author: 朱佳睿
     * @Time: 2023.04.11
     */
    RespBean sellProduct(ProductParam productParam);

    /**
     * 删除发布的商品
     * @param userId
     * @param productId
     * @return
     */
    RespBean deleteCollect(Integer userId, Integer productId);
}
