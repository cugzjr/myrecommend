package com.xxxx.server.service.impl;

import com.xxxx.server.dao.BuyRespository;
import com.xxxx.server.dao.ProductRespository;
import com.xxxx.server.dao.UserRespository;
import com.xxxx.server.pojo.Product;
import com.xxxx.server.pojo.RespBean;
import com.xxxx.server.pojo.User;
import com.xxxx.server.relation.BuyRelation;
import com.xxxx.server.service.BuyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 购买商品接口实现
 * @Author: 朱佳睿
 * @Time: 2023.03.29
 */
@Service
public class BuyServiceImpl implements BuyService {
    @Autowired
    private ProductRespository productRespository;
    @Autowired
    private UserRespository userRespository;
    @Autowired
    private BuyRespository buyRespository;

    /**
     * 购买商品
     * @param userId
     * @param productId
     * @return RespBean
     * @Author: 朱佳睿
     * @Time: 2023.03.29
     */
    @Override
    public RespBean buyProduct(Integer userId, Integer productId)
    {
        User user = userRespository.findByUserId(userId);
        Product product = productRespository.findByProductId(productId);
        BuyRelation buyRelation = BuyRelation.builder().user(user).product(product).relation("购买").build();
        buyRespository.save(buyRelation);
        return RespBean.success("购买成功");
    }
}
