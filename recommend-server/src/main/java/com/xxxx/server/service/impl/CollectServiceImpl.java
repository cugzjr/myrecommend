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

@Service
public class CollectServiceImpl implements CollectService {
    @Autowired
    private UserRespository userRespository;
    @Autowired
    private ProductRespository productRespository;
    @Autowired
    private CollectRespository collectRespository;
    @Override
    public RespBean collectProduct(Integer userId, Integer productId)
    {
        User user = userRespository.findByUserId(userId);
        Product product = productRespository.findByProductId(productId);
        CollectRelation collectRelation = CollectRelation.builder().user(user).product(product).relation("收藏").build();
        collectRespository.save(collectRelation);
        return RespBean.success("收藏成功");
    }

}
