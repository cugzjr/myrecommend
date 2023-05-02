package com.xxxx.server.service.impl;

import com.mongodb.client.result.UpdateResult;
import com.xxxx.server.mongopojo.Rating;
import com.xxxx.server.param.RatingParam;
import com.xxxx.server.service.RatingService;
import com.xxxx.server.utils.Constant;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;

import javax.annotation.Resource;

@Service
public class RatingServiceImpl implements RatingService {
    @Resource
    private MongoTemplate mongoTemplate;

    /**
     * 商品评分
     * @param ratingParam 评分信息
     * @return 评分结果
     */
    @Override
    public boolean productRating(RatingParam ratingParam){
        // 更新redis
        updateRedis(ratingParam);
        // 更新mongodb
        if (ratingExist(ratingParam.getUserId(), ratingParam.getProductId())) {
            return updateRating(ratingParam);
        } else {
            return newRating(ratingParam);
        }
    }

    /**
     * 更新redis
     * @param ratingParam 评分数据
     */
    @Override
    public void updateRedis(RatingParam ratingParam){
        Jedis jedis = new Jedis(Constant.REDIS_HOST, Constant.REDIS_PORT);
        jedis.auth(Constant.REDIS_PASSWORD);
        if (jedis.exists("userId:" + ratingParam.getUserId()) && jedis.llen("userId:" + ratingParam.getUserId()) >= Constant.REDIS_PRODUCT_RATING_QUEUE_SIZE) {
            jedis.rpop("userId:" + ratingParam.getUserId());
        }
        jedis.lpush("userId:" + ratingParam.getUserId(), ratingParam.getProductId() + ":" + ratingParam.getScore());
    }

    /**
     * 检查用户是否给某个商品评过分
     * @param userId 用户Id
     * @param productId 商品Id
     * @return 检查结果
     */
    @Override
    public boolean ratingExist(Integer userId, Integer productId){
        Query query = new Query();
        query.addCriteria(Criteria.where("userId").is(userId)
                .and("productId").is(productId));
        return mongoTemplate.exists(query, Rating.class, Constant.MONGODB_RATING_COLLECTION);
    }

    /**
     * 更新评分记录
     * @param ratingParam 评分记录
     * @return 更新结果
     */
    @Override
    public boolean updateRating(RatingParam ratingParam){
        Query query = new Query();
        query.addCriteria(Criteria.where("userId").is(ratingParam.getUserId())
                .and("productId").is(ratingParam.getProductId()));
        Update update = new Update();
        update.set("score", ratingParam.getScore());
        update.set("timestamp",(int)System.currentTimeMillis()/1000);
        UpdateResult result = mongoTemplate.updateFirst(query, update, Rating.class, Constant.MONGODB_RATING_COLLECTION);
        return result.getModifiedCount() > 0;
    }

    @Override
    public boolean newRating(RatingParam ratingParam){
        Rating rating = new Rating();
        rating.setUserId(ratingParam.getUserId());
        rating.setProductId(ratingParam.getProductId());
        rating.setScore(ratingParam.getScore());
        rating.setTimestamp((int) (System.currentTimeMillis()/1000));
        mongoTemplate.insert(rating, Constant.MONGODB_RATING_COLLECTION);
        return true;
    }
}
