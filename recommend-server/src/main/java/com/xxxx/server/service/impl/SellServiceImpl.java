package com.xxxx.server.service.impl;

import com.xxxx.server.Param.ProductParam;
import com.xxxx.server.dao.ProductRespository;
import com.xxxx.server.dao.SellRespository;
import com.xxxx.server.dao.UserRespository;
import com.xxxx.server.pojo.Product;
import com.xxxx.server.pojo.RespBean;
import com.xxxx.server.pojo.User;
import com.xxxx.server.relation.SellRelation;
import com.xxxx.server.service.SellService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SellServiceImpl implements SellService {
    @Autowired
    private ProductRespository productRespository;
    @Autowired
    private SellRespository sellRespository;
    @Autowired
    private UserRespository userRespository;

    @Override
    public RespBean sellProduct(ProductParam productParam)
    {
        Product product = new Product();
        product.setProductId(productParam.getProductId());
        product.setName(productParam.getName());
        productRespository.save(product);
        User user = userRespository.findByUserId(productParam.getUserId());  //用户
        SellRelation sellRelation = SellRelation.builder().user(user).product(product).relation("出售").build();
        sellRespository.save(sellRelation);
        return RespBean.success("发布成功");
    }
}
