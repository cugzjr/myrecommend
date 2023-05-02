package com.xxxx.server.service.impl;

import com.xxxx.server.mongopojo.*;
import com.xxxx.server.mongopojo.RateMoreRecentlyProduct;
import com.xxxx.server.service.RecommendService;
import com.xxxx.server.utils.Constant;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;

import javax.annotation.Resource;
import java.util.ArrayList;
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
        List<RateMoreRecentlyProduct> result = mongoTemplate.findAll(RateMoreRecentlyProduct.class, Constant.MONGODB_RATE_MORE_PRODUCTS_RECENTLY_COLLECTION);
        // 当前页面的商品
        List<Integer> needProductList = new ArrayList<>();
        if(page>=result.size()){
            return needProductList;
        }
        // 一页商品的数量
        int count = 10;
        int i= page * 5;
        while(count>0)
        {
            try {
                needProductList.add(result.get(i).getProductId());
            } catch (Exception e) {
                return needProductList;
            }
            i ++;
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
        List<Integer> res = new ArrayList<>();
        Query query = new Query();
        query.addCriteria(Criteria.where("userId").is(userId));
        UserRecs userRecs =  mongoTemplate.findOne(query, UserRecs.class, Constant.MONGODB_USER_RECS_COLLECTION);
        // 离线推荐结果集和
        List<ProductScore> scores = userRecs.getRecs();
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

    /**
     * 基于内容的推荐
     * @param productId 商品Id
     * @return 推荐列表
     */
    @Override
    public List<ProductScore> getContentBasedProductRecs(Integer productId){
        Query query = new Query();
        query.addCriteria(Criteria.where("productId").is(productId));
        ContentBasedProduct contentBasedProduct = mongoTemplate.findOne(query, ContentBasedProduct.class, Constant.MONGODB_CONTENTBASED_COLLECTION);
        if(contentBasedProduct != null){
            return contentBasedProduct.getRecs();
        }
        return null;
    }

    /**
     * 基于物品的协同过滤推荐
     * @param productId 商品Id
     * @return 推荐列表
     */
    @Override
    public List<ProductScore> getItemcfProductRecs(Integer productId){
        Query query = new Query();
        query.addCriteria(Criteria.where("productId").is(productId));
        ItemcfProduct itemcfProduct = mongoTemplate.findOne(query, ItemcfProduct.class, Constant.MONGODB_ITEMCF_COLLECTION);
        if(itemcfProduct != null){
            return itemcfProduct.getRecs();
        }
        return null;
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
        List<ProductScore> contentProducts = getContentBasedProductRecs(productId);
        // 基于物品协同推荐的列表
        List<ProductScore> itemcfProducts = getItemcfProductRecs(productId);
        for(ProductScore productScore:contentProducts){
            if(!allProducts.contains(productScore.getProductId())){
                allProducts.add(productScore.getProductId());
            }
        }
        for(ProductScore productScore:itemcfProducts){
            if(!allProducts.contains(productScore.getProductId())){
                allProducts.add(productScore.getProductId());
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
        List<Integer> res = new ArrayList<>();
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
        // 某一页所需的推荐
//        List<Integer> needProductList = new ArrayList<>();
//        if(page>=scores.size()){
//            return needProductList;
//        }
//        // 一页商品的数量
//        int count = 10;
//        int i= page * 10;
//        while(count>0)
//        {
//            try {
//                needProductList.add(scores.get(i).getProductId());
//            } catch (Exception e) {
//                return needProductList;
//            }
//            i ++;
//            -- count;
//        }
        List<Integer> needProductList = new ArrayList<>();
        if(page>=scores.size()){
            return needProductList;
        }
        // 一页商品的数量
        int count = 10;
        int i= scores.size() - page * count - 1;
        while(count>0)
        {
            try {
                needProductList.add(scores.get(i).getProductId());
            } catch (Exception e) {
                return needProductList;
            }
            i --;
            -- count;
        }
        return needProductList;
    }
}
