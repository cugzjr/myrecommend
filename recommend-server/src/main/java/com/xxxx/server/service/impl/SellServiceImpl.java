package com.xxxx.server.service.impl;

import com.xxxx.server.param.ProductParam;
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

/**
 * 出售商品接口实现
 * @Author: 朱佳睿
 * @Time: 2023.03.29
 */
@Service
public class SellServiceImpl implements SellService {
    @Autowired
    private ProductRespository productRespository;
    @Autowired
    private SellRespository sellRespository;
    @Autowired
    private UserRespository userRespository;

    /**
     * 出售商品
     * @param productParam
     * @return RespBean
     * @Author: 朱佳睿
     * @Time: 2023.03.29
     */
    @Override
    public RespBean sellProduct(ProductParam productParam)
    {
        Product product = new Product();
        product.setProductId(productParam.getProductId());
        product.setName(productParam.getName());
        productRespository.save(product);
        User user = userRespository.findByUserId(productParam.getUserId());
        SellRelation sellRelation = SellRelation.builder().user(user).product(product).relation("出售").build();
        sellRespository.save(sellRelation);
        return RespBean.success("发布成功");
    }

    @Override
    public RespBean deleteCollect(Integer userId, Integer productId){
        sellRespository.deleteBuyRelation(userId, productId);
        return RespBean.success("删除成功");
    }
}
