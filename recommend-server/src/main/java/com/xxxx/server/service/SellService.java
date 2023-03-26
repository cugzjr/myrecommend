package com.xxxx.server.service;

import com.xxxx.server.Param.ProductParam;
import com.xxxx.server.pojo.RespBean;

public interface SellService {
    RespBean sellProduct(ProductParam productParam);
}
