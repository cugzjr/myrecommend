package com.xxxx.server.controller;

import org.springframework.boot.test.context.SpringBootTest;
import org.testng.annotations.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import static org.testng.Assert.*;

@SpringBootTest
public class CollectControllerTest extends AbstractTestNGSpringContextTests {
    @Autowired
    private CollectController collectController;

    @Test
    public void CollectTest(){
        Integer userId = 33, productId = 5;
        assertEquals(collectController.collectProduct(userId, productId).getMessage(), "收藏成功");
    }

    @Test
    public void DeleteTest(){
        Integer userId = 33, productId = 72;
        assertEquals(collectController.deleteCollect(userId, productId).getMessage(), "删除成功");
    }
}
