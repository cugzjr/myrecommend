package com.xxxx.server.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;
import static org.testng.Assert.*;

@SpringBootTest
public class BuyControllerTest extends AbstractTestNGSpringContextTests {
    @Autowired
    private BuyController buyController;
    /**
     * 购买商品接口测试
     */
    @Test
    public void BuyProductTest(){
        Integer userId = 33, productId = 5;
        assertEquals(buyController.buyProduct(userId, productId).getMessage(), "购买成功");
    }
}
