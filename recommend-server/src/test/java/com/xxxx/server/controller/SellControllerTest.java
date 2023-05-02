package com.xxxx.server.controller;

import com.xxxx.server.param.ProductParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

@SpringBootTest
public class SellControllerTest extends AbstractTestNGSpringContextTests {
    @Autowired
    private SellController sellController;

    /**
     * 发布商品接口测试
     */
    @Test
    public void SellProductTest(){
        ProductParam productParam = new ProductParam();
        productParam.setUserId(5);
        productParam.setProductId(110);
        productParam.setName("手机");
        assertEquals(sellController.sellProduct(productParam).getMessage(), "发布成功");
    }

    /**
     * 删除发布接口测试
     */
    @Test
    public void DeleteProductTest(){
        assertEquals(sellController.deleteProduct(5, 110).getMessage(), "删除成功");
    }
}
