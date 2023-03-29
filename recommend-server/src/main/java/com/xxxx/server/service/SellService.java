package com.xxxx.server.service;

import com.xxxx.server.Param.ProductParam;
import com.xxxx.server.pojo.RespBean;

/**
 * 出售商品相关操作接口
 * @Author: 朱佳睿
 * @Time: 2023.03.29
 */
public interface SellService {
    RespBean sellProduct(ProductParam productParam);
}
