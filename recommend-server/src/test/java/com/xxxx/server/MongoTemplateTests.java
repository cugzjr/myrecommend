package com.xxxx.server;

import com.xxxx.server.mongopojo.*;
import com.xxxx.server.mongopojo.RateMoreRecentlyProduct;
import com.xxxx.server.param.RatingParam;
import com.xxxx.server.service.RatingService;
import com.xxxx.server.service.RecommendService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.test.context.junit4.SpringRunner;
import redis.clients.jedis.Jedis;

import javax.annotation.Resource;
import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class MongoTemplateTests {
    @Autowired
    private RecommendService recommendService;
    @Autowired
    private RatingService ratingService;
    @Resource
    private MongoTemplate mongoTemplate;

    /**
     * 检查用户是否是新人用户
     */
    @Test
    public void test(){
        System.out.print(recommendService.checkUserNew(3000));
    }

    /**
     * 离线推荐测试
     */
    @Test
    public void testList(){
        List<Integer> res = recommendService.getUserRecsProducts(784, 0);
        for(Integer temp:res){
            System.out.print(temp);
            System.out.print("\n");
        }
    }

    /**
     * 基于内容的推荐测试
     */
    @Test
    public void contentRecommendTest(){
        List<ProductScore> productScoreList = recommendService.getContentBasedProductRecs(23);
        for(ProductScore productScore:productScoreList){
            System.out.print(productScore.getProductId());
            System.out.print("\n");
        }
    }

    /**
     * 基于物品的协同过滤测试
     */
    @Test
    public void itemCFRecommendTest(){
        List<ProductScore> productScoreList = recommendService.getItemcfProductRecs(1);
        for(ProductScore productScore:productScoreList){
            System.out.print(productScore.getProductId());
            System.out.print("\n");
        }
    }

    /**
     * 测试redis连接
     */
    @Test
    public void testRedis(){
        Jedis jedis = new Jedis("123.249.11.83", 8080);
        jedis.auth("Zjr010205@");
        System.out.println("连接成功");
        System.out.println("服务正在运行"+jedis.ping());
    }

    /**
     * 测试评分是否存在
     */
    @Test
    public void ratingExist(){
        Query query = new Query();
        query.addCriteria(Criteria.where("userId").is(30)
                .and("productId").is(15));
        Rating rating = mongoTemplate.findOne(query, Rating.class, "Rating");
        System.out.println(rating.getScore());
        System.out.println(rating.getTimestamp());
    }

    /**
     * 测试评分记录修改
     */
    @Test
    public void rating(){
        RatingParam ratingParam = new RatingParam();
        ratingParam.setUserId(643);
        ratingParam.setProductId(21);
        ratingParam.setScore(3.0);
        System.out.println(ratingService.updateRating(ratingParam));
    }

    @Test
    public void insert(){
        RatingParam ratingParam = new RatingParam();
        ratingParam.setUserId(20001);
        ratingParam.setProductId(5);
        ratingParam.setScore(5.0);
        System.out.println(ratingService.newRating(ratingParam));
    }

}
