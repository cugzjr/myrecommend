package com.xxxx.server.service;

import com.xxxx.server.pojo.RespBean;

public interface CollectService {
    RespBean collectProduct(Integer userId, Integer productId);
}
