package com.xxxx.server.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

import java.util.Optional;

import static org.testng.Assert.*;

@SpringBootTest
public class FocusControllerTest extends AbstractTestNGSpringContextTests {
    @Autowired
    private FocusController focusController;

    /**
     * 关注用户接口测试
     */
    @Test
    public void FocusUserTest(){
        Integer startId = 22, endId = 1028;
        assertEquals(focusController.focusUser(startId, endId).getMessage(), "关注成功");
    }

    /**
     * 获取关注数量接口测试
     */
    @Test
    public void GetFocusNumTest(){
        assertEquals(focusController.getFocusnum(12).intValue(), 10);
        assertEquals(focusController.getFocusnum(407).intValue(), 0);
    }

    /**
     * 获取粉丝数量接口测试
     */
    @Test
    public void GetFansNumTest(){
        assertEquals(focusController.getFansnum(5).intValue(), 7);
        assertEquals(focusController.getFansnum(321).intValue(), 0);
    }

    /**
     * 删除关注接口侧测试
     */
    @Test
    public void DeleteFocusTest(){
        assertEquals(focusController.deleteFocus(3, 1).getMessage(), "删除成功");
    }

    /**
     * 判断互关接口测试
     */
    @Test
    public void CheckBothFocusTest(){
        assertEquals(focusController.checkBothFocus(5, 11).getObject(), true);
        assertEquals(focusController.checkBothFocus(1, 11).getObject(), false);
    }
}
