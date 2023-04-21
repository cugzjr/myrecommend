package com.xxxx.server.service;

import com.xxxx.server.pojo.RespBean;
import java.util.List;

/**
 * 用户相关操作接口
 * @Author: 朱佳睿
 * @Time: 2023.03.29
 */
public interface UserService {

    /**
     * 注册
     * @param userId
     * @param name
     * @return 注册结果
     * @Author: 朱佳睿
     * @Time: 2023.04.11
     */
    RespBean register(Integer userId, String name);

    /**
     * 修改用户姓名
     * @param userId
     * @param name
     * @return 修改结果
     * @Author: 朱佳睿
     * @Time: 2023.04.11
     */
    RespBean changeName(Integer userId, String name);

    /**
     * 获取我的关注
     * @param userId
     * @return 我的关注列表
     * @Author: 朱佳睿
     * @Time: 2023.04.11
     */
    List<Integer> getAllFollwings(Integer userId);

    /**
     * 获取我的粉丝
     * @param userId
     * @return 我的粉丝列表
     * @Author: 朱佳睿
     * @Time: 2023.04.11
     */
    List<Integer> getAllFollwers(Integer userId);

    /**
     * 推荐感兴趣的人
     * @param userId
     * @return 推荐列表
     * @Author: 朱佳睿
     * @Time: 2023.04.11
     */
    List<Integer> recommendUsers(Integer userId);
}
