package com.xxxx.server.service;

import com.xxxx.server.pojo.RespBean;

public interface BuyService {
    RespBean buyProduct(Integer userId, Integer productId);
}
