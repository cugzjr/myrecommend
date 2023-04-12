package com.xxxx.server.service.impl;

import com.xxxx.server.dao.CollectRespository;
import com.xxxx.server.dao.ProductRespository;
import com.xxxx.server.dao.UserRespository;
import com.xxxx.server.pojo.Product;
import com.xxxx.server.pojo.RespBean;
import com.xxxx.server.pojo.User;
import com.xxxx.server.relation.CollectRelation;
import com.xxxx.server.service.CollectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 收藏商品接口实现
 * @Author: 朱佳睿
 * @Time: 2023.03.29
 */
@Service
public class CollectServiceImpl implements CollectService {
    @Autowired
    private UserRespository userRespository;
    @Autowired
    private ProductRespository productRespository;
    @Autowired
    private CollectRespository collectRespository;

    /**
     * 收藏商品
     * @param userId
     * @param productId
     * @return RespBean
     * @Author: 朱佳睿
     * @Time: 2023.03.29
     */
    @Override
    public RespBean collectProduct(Integer userId, Integer productId)
    {
        int a = 2;
        User user = userRespository.findByUserId(userId);
        Product product = productRespository.findByProductId(productId);
        CollectRelation collectRelation = CollectRelation.builder().user(user).product(product).relation("收藏").build();
        collectRespository.save(collectRelation);
        return RespBean.success("收藏成功");
    }

}
