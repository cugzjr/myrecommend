package com.xxxx.server.service;

import com.xxxx.server.param.RatingParam;

public interface RatingService {
    /**
     * 商品评分
     * @param ratingParam 评分记录
     * @return 评分结果
     */
    boolean productRating(RatingParam ratingParam);

    /**
     * 更新Redis
     * @param ratingParam 评分记录
     */
    void updateRedis(RatingParam ratingParam);

    /**
     * 检查评分是否存在
     * @param userId 用户Id
     * @param productId 商品Id
     * @return 检查结果
     */
    boolean ratingExist(Integer userId, Integer productId);

    /**
     * 更新评分记录
     * @param ratingParam 评分记录
     * @return 更新结果
     */
    boolean updateRating(RatingParam ratingParam);

    /**
     * 新建评分记录
     * @param ratingParam 评分记录
     * @return 新建结果
     */
    boolean newRating(RatingParam ratingParam);
}
