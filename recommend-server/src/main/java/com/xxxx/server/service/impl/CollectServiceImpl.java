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

import java.util.ArrayList;
import java.util.List;

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
        User user = userRespository.findByUserId(userId);
        Product product = productRespository.findByProductId(productId);
        CollectRelation collectRelation = CollectRelation.builder().user(user).product(product).relation("收藏").build();
        collectRespository.save(collectRelation);
        return RespBean.success("收藏成功");
    }

    @Override
    public RespBean deleteCollect(Integer userId, Integer productId){
        collectRespository.deleteCollectRelation(userId, productId);
        return RespBean.success("删除成功");
    }
    @Override
    public RespBean checkCollect(Integer userId, Integer productId){
        if(collectRespository.checkCollect(userId, productId) == 0){
            return RespBean.success("获取成功", false);
        }
        return RespBean.success("获取成功", true);
    }
    @Override
    public RespBean getCollectNum(Integer productId){
        return RespBean.success("获取成功", collectRespository.getProductCollectCount(productId));
    }
    @Override
    public List<Integer> getMyCollect(Integer userId, Integer page){
        List<Product> products = collectRespository.findCollectProductsByUserId(userId);
        // 当前页面的商品
        List<Integer> needProductList = new ArrayList<>();
        if(page>=products.size()){
            return needProductList;
        }
        // 一页商品的数量
        int count = 10;
        int i= page * count;
        while(count>0)
        {
            try {
                needProductList.add(products.get(i).getProductId());
            } catch (Exception e) {
                return needProductList;
            }
            i ++;
            -- count;
        }
        return needProductList;
    }
}
