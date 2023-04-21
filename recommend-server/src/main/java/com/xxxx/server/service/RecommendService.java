package com.xxxx.server.service;

import com.xxxx.server.mongopojo.ProductScore;
import java.util.List;

public interface RecommendService {
    /**
     * 获取热门商品
     * @param page 页码
     * @return 热门商品列表
     */
    List<Integer> getHotProducts(Integer page);

    /**
     * 基于用户历史行为推荐商品
     * @param userId 用户Id
     * @param page 页码
     * @return 商品列表
     */
    List<Integer> getUserRecsProducts(Integer userId, Integer page);

    /**
     * 判断用户是否有过浏览记录
     * @param userId 用户Id
     * @return 判断结果
     */
    boolean checkUserNew(Integer userId);

    /**
     * 基于商品内容的推荐
     * @param productId 商品Id
     * @return 推荐列表
     */
    List<ProductScore> getContentBasedProductRecs(Integer productId);

    /**
     * 基于物品的协同过滤的推荐
     * @param productId 商品Id
     * @return 推荐列表
     */
    List<ProductScore> getItemcfProductRecs(Integer productId);

    /**
     * 商品详情页推荐
     * @param productId 商品Id
     * @param page 页码
     * @return 推荐列表
     */
    List<Integer> detailRecommend(Integer productId, Integer page);

    /**
     * 实时推荐
     * @param userId 用户Id
     * @param page 页码
     * @return 推荐列表
     */
    List<Integer> onlineRecommend(Integer userId, Integer page);
}
