package com.xxxx.server.controller;

import com.xxxx.server.pojo.RespBean;
import com.xxxx.server.service.FocusService;
import com.xxxx.server.service.UserService;
import io.swagger.annotations.ApiOperation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.apache.log4j.Logger;
import java.util.List;

/**
 * 收藏接口类
 * @Author: 朱佳睿
 * @Time: 2023.03.26
 */
@RestController
@RequestMapping("/focus")
public class FocusController {
    private Logger logger = Logger.getLogger(FocusController.class.getName());
    @Autowired
    private FocusService focusService;
    @Autowired
    private UserService userService;

    /**
     * 关注用户接口
     * @param startId
     * @param endId
     * @return: RespBean
     * @Author: 朱佳睿
     * @Time: 2023.03.26
     */
    @ApiOperation(value = "关注用户")
    @PostMapping("/focususer")
    public RespBean focusUser(@RequestParam("startId") Integer startId, @RequestParam("endId") Integer endId)
    {
        return focusService.focusUser(startId, endId);
    }

    /**
     * 我的关注接口
     * @param userId
     * @return: List<Integer>
     * @Author: 朱佳睿
     * @Time: 2023.03.26
     */
    @ApiOperation(value = "我的关注")
    @PostMapping("/myfocus")
    public List<Integer> getFocus(@RequestParam("userId") Integer userId){
        return userService.getAllFollwings(userId);
    }

    /**
     * 我的关注总数接口
     * @param userId
     * @return: Integer
     * @Author: 朱佳睿
     * @Time: 2023.03.26
     */
    @ApiOperation(value = "我的关注数")
    @PostMapping("/myfocusnum")
    public Integer getFocusnum(@RequestParam("userId") Integer userId){
        List<Integer> res = userService.getAllFollwings(userId);
        if(res == null)
        {
            return 0;
        }
        else {
            return res.size();
        }
    }

    /**
     * 我的粉丝接口
     * @param userId
     * @return: List<Integer>
     * @Author: 朱佳睿
     * @Time: 2023.03.26
     */
    @ApiOperation(value = "我的粉丝")
    @PostMapping("/myfans")
    public List<Integer> getFans(@RequestParam("userId") Integer userId){
        return userService.getAllFollwers(userId);
    }

    /**
     * 我的粉丝数量接口
     * @param userId
     * @return: Integer
     * @Author: 朱佳睿
     * @Time: 2023.03.26
     */
    @ApiOperation(value = "我的粉丝数")
    @PostMapping("/myfansnum")
    public Integer getFansnum(@RequestParam("userId") Integer userId){
        logger.info("PRODUCT_RATING_PREFIX: " + "2|4|5" + "|" + System.currentTimeMillis() / 1000);
        List<Integer> res = userService.getAllFollwers(userId);
        if(res == null) {
            return 0;
        }
        else {
            return res.size();
        }
    }

    /**
     * 删除关注接口
     * @param startId
     * @param endId
     * @return 删除结果
     * @Author: 朱佳睿
     * @Time: 2023.04.12
     */
    @ApiOperation(value = "删除关注")
    @DeleteMapping
    public RespBean deleteFocus(@RequestParam("startId") Integer startId, @RequestParam("endId") Integer endId)
    {
        return focusService.deleteFocus(startId, endId);
    }
}
