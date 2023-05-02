package com.xxxx.server.service.impl;

import com.xxxx.server.mongopojo.*;
import com.xxxx.server.service.RecommendService;
import com.xxxx.server.utils.Constant;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Service
public class RecommendServiceImpl implements RecommendService {
    @Resource
    private MongoTemplate mongoTemplate;

    /**
     * 返回某一页的推荐商品（热门推荐）
     * @param page 页码
     * @return 推荐结果
     */
    @Override
    public List<Integer> getHotProducts(Integer page)
    {
//        List<RateMoreRecentlyProduct> result = mongoTemplate.findAll(RateMoreRecentlyProduct.class, Constant.MONGODB_RATE_MORE_PRODUCTS_RECENTLY_COLLECTION);
        List<Integer> result = new ArrayList<>();
        Jedis jedis = new Jedis(Constant.REDIS_HOST, Constant.REDIS_PORT);
        jedis.auth(Constant.REDIS_PASSWORD);
        String redisKey = "HomeRecommend:" ;
        List<String> redisRecs = jedis.lrange(redisKey, 0, -1);
        jedis.close();
        for (String redisRec : redisRecs) {
            String[] parts = redisRec.split(":");
            int productId = Integer.parseInt(parts[0]);
            result.add(productId);
        }
        // 当前页面的商品
        List<Integer> needProductList = new ArrayList<>();
        if(page>=result.size()){
            return needProductList;
        }
        // 一页商品的数量
        int count = 10;
        int i= result.size() - page * 10 - 1;
        while(count>0)
        {
            try {
                needProductList.add(result.get(i));
            } catch (Exception e) {
                return needProductList;
            }
            i --;
            -- count;
        }
        return needProductList;
    }

    /**
     * 检查一个用户是否是新人用户
     * @param userId 用户Id
     * @return 检查结果
     */
    @Override
    public boolean checkUserNew(Integer userId){
        Query query = new Query();
        query.addCriteria(Criteria.where("userId").is(userId));
        return mongoTemplate.exists(query, Rating.class, Constant.MONGODB_RATING_COLLECTION);
    }

    /**
     * 离线推荐
     * @param userId 用户Id
     * @param page 页码
     * @return 第page页推荐的内容
     */
    @Override
    public List<Integer> getUserRecsProducts(Integer userId, Integer page){
//        Query query = new Query();
//        query.addCriteria(Criteria.where("userId").is(userId));
//        UserRecs userRecs =  mongoTemplate.findOne(query, UserRecs.class, Constant.MONGODB_USER_RECS_COLLECTION);
//        // 离线推荐结果集和
//        List<ProductScore> scores = userRecs.getRecs();
        Jedis jedis = new Jedis(Constant.REDIS_HOST, Constant.REDIS_PORT);
        jedis.auth(Constant.REDIS_PASSWORD);
        String redisKey = "OfflineRecommend:" + userId;
        List<String> redisRecs = jedis.lrange(redisKey, 0, -1);
        jedis.close();
        List<Integer> scores = new ArrayList<>();
        for (String redisRec : redisRecs) {
            String[] parts = redisRec.split(":");
            int productId = Integer.parseInt(parts[0]);
            scores.add(productId);
        }
        // 某一页所需的推荐
        List<Integer> needProductList = new ArrayList<>();
        if(page>=scores.size()){
            return needProductList;
        }
        // 一页商品的数量
        int count = 10;
        int i= scores.size() - page * 10 - 1;
        while(count>0)
        {
            try {
                needProductList.add(scores.get(i));
            } catch (Exception e) {
                return needProductList;
            }
            i --;
            -- count;
        }
        return needProductList;
    }

    /**
     * 基于内容的推荐
     * @param productId 商品Id
     * @return 推荐列表
     */
    @Override
    public List<Integer> getContentBasedProductRecs(Integer productId){
//        Query query = new Query();
//        query.addCriteria(Criteria.where("productId").is(productId));
//        ContentBasedProduct contentBasedProduct = mongoTemplate.findOne(query, ContentBasedProduct.class, Constant.MONGODB_CONTENTBASED_COLLECTION);
//        if(contentBasedProduct != null){
//            return contentBasedProduct.getRecs();
//        }
//        return null;
        List<Integer> res = new ArrayList<>();
        Jedis jedis = new Jedis(Constant.REDIS_HOST, Constant.REDIS_PORT);
        jedis.auth(Constant.REDIS_PASSWORD);
        String redisKey = "ContentRecommend:" + productId;
        List<String> redisRecs = jedis.lrange(redisKey, 0, -1);
        jedis.close();
        for (String redisRec : redisRecs) {
            String[] parts = redisRec.split(":");
            int productid = Integer.parseInt(parts[0]);
            res.add(productid);
        }
        Collections.reverse(res);
        return res;
    }

    /**
     * 基于物品的协同过滤推荐
     * @param productId 商品Id
     * @return 推荐列表
     */
    @Override
    public List<Integer> getItemcfProductRecs(Integer productId){
//        Query query = new Query();
//        query.addCriteria(Criteria.where("productId").is(productId));
//        ItemcfProduct itemcfProduct = mongoTemplate.findOne(query, ItemcfProduct.class, Constant.MONGODB_ITEMCF_COLLECTION);
//        if(itemcfProduct != null){
//            return itemcfProduct.getRecs();
//        }
//        return null;
        List<Integer> res = new ArrayList<>();
        Jedis jedis = new Jedis(Constant.REDIS_HOST, Constant.REDIS_PORT);
        jedis.auth(Constant.REDIS_PASSWORD);
        String redisKey = "ItemCFRecommend:" + productId;
        List<String> redisRecs = jedis.lrange(redisKey, 0, -1);
        jedis.close();
        for (String redisRec : redisRecs) {
            String[] parts = redisRec.split(":");
            int productid = Integer.parseInt(parts[0]);
            res.add(productid);
        }
        Collections.reverse(res);
        return res;
    }

    /**
     * 详情页推荐
     * @param productId 商品Id
     * @param page 页码
     * @return 推荐列表
     */
    @Override
    public List<Integer> detailRecommend(Integer productId, Integer page){
        List<Integer> allProducts = new ArrayList<>();
        // 基于内容推荐的列表
        List<Integer> contentProducts = getContentBasedProductRecs(productId);
        // 基于物品协同推荐的列表
        List<Integer> itemcfProducts = getItemcfProductRecs(productId);
        for(Integer productid:contentProducts){
            if(!allProducts.contains(productid)){
                allProducts.add(productid);
            }
        }
        for(Integer productid:itemcfProducts){
            if(!allProducts.contains(productid)){
                allProducts.add(productid);
            }
        }
        // 某一页所需的推荐
        List<Integer> needProductList = new ArrayList<>();
        if(page>=allProducts.size()){
            return needProductList;
        }
        // 一页商品的数量
        int count = 10;
        int i= page * 10;
        while(count>0)
        {
            try {
                needProductList.add(allProducts.get(i));
            } catch (Exception e) {
                return needProductList;
            }
            i ++;
            -- count;
        }
        return needProductList;
    }

    /**
     * 实时推荐
     * @param userId 用户Id
     * @param page 页码
     * @return 推荐列表
     */
    @Override
    public List<Integer> onlineRecommend(Integer userId, Integer page){
//        Query query = new Query();
//        query.addCriteria(Criteria.where("userId").is(userId));
//        StreamRecsProduct streamRecsProduct =  mongoTemplate.findOne(query, StreamRecsProduct.class, Constant.MONGODB_STREAM_RECS_COLLECTION);
//        // 离线推荐结果集和
//        List<ProductScore> scores = streamRecsProduct.getRecs();
        Jedis jedis = new Jedis(Constant.REDIS_HOST, Constant.REDIS_PORT);
        jedis.auth(Constant.REDIS_PASSWORD);
        String redisKey = "StreamRecs:" + userId;
        List<String> redisRecs = jedis.lrange(redisKey, 0, -1);
        jedis.close();
        // 解析Redis中的推荐结果
        List<ProductScore> scores = new ArrayList<>();
        for (String redisRec : redisRecs) {
            String[] parts = redisRec.split(":");
            int productId = Integer.parseInt(parts[0]);
            double score = Double.parseDouble(parts[1]);
            ProductScore productScore = new ProductScore();
            productScore.setProductId(productId);
            productScore.setScore(score);
            scores.add(productScore);
        }
        // 按评分降序排列
        scores.sort(new Comparator<ProductScore>() {
            @Override
            public int compare(ProductScore ps1, ProductScore ps2) {
                // 比较评分，按降序排列
                return Double.compare(ps2.getScore(), ps1.getScore());
            }
        });
        // 某一页所需的推荐
        List<Integer> needProductList = new ArrayList<>();
        if(page>=scores.size()){
            return needProductList;
        }
        // 一页商品的数量
        int count = 10;
        int i= page * 10;
        while(count>0)
        {
            try {
                needProductList.add(scores.get(i).getProductId());
            } catch (Exception e) {
                return needProductList;
            }
            i ++;
            -- count;
        }
        return needProductList;
    }
}
