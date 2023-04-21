package com.xxxx.server.utils;

/**
 * 常量配置类
 */
public class Constant {
    //************** FOR MONGODB ****************
    public static String MONGODB_RATING_COLLECTION = "Rating";
    public static String MONGODB_RATE_MORE_PRODUCTS_RECENTLY_COLLECTION = "RateMoreRecentlyProducts";
    public static String MONGODB_USER_RECS_COLLECTION = "UserRecs";
    public static String MONGODB_ITEMCF_COLLECTION = "ItemCFProductRecs";
    public static String MONGODB_CONTENTBASED_COLLECTION = "ContentBasedProductRecs";
    public static String MONGODB_STREAM_RECS_COLLECTION = "StreamRecs";

    //************** FOR REDIS ****************
    public static String REDIS_HOST = "123.249.11.83";
    public static int REDIS_PORT = 8080;
    public static String REDIS_PASSWORD = "Zjr010205@";

    //************** FOR Limits ****************
    public static int REDIS_PRODUCT_RATING_QUEUE_SIZE = 40;
    public static int FRIENDS_RECOMMEND_SIZE = 10;
    public static String PRODUCT_RATING_PREFIX = "PRODUCT_RATING_PREFIX";

}
