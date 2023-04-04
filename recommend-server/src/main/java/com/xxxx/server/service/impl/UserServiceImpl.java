package com.xxxx.server.service.impl;

import com.xxxx.server.dao.UserRespository;
import com.xxxx.server.pojo.RespBean;
import com.xxxx.server.pojo.User;
import com.xxxx.server.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

/**
 * 用户操作接口实现
 * @Author: 朱佳睿
 * @Time: 2023.03.29
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRespository userRespository;

    /**
     * 注册
     * @param userId
     * @param name
     * @return RespBean
     * @Author: 朱佳睿
     * @Time: 2023.03.29
     */
    @Override
    public RespBean register(Integer userId, String name)
    {
        User user = new User();
        user.setUserId(userId);
        user.setName(name);
        userRespository.save(user);
        return RespBean.success("注册成功");
    }

    /**
     * 修改姓名
     * @param userId
     * @param name
     * @return RespBean
     * @Author: 朱佳睿
     * @Time: 2023.03.29
     */
    @Override
    public RespBean changeName(Integer userId, String name)
    {
        User user = userRespository.findByUserId(userId);
        user.setName(name);
        User newuser = userRespository.updateByNode(user);
        if(newuser != null) return RespBean.success("修改成功");
        else return RespBean.error("修改失败");
    }

    /**
     * 获取我的关注
     * @param userId
     * @return List<Integer>
     * @Author: 朱佳睿
     * @Time: 2023.03.29
     */
    @Override
    public List<Integer> getAllFollwings(Integer userId)  //获取我的关注
    {
        List<User> userList = userRespository.getAllFollowings(userId);
        List<Integer> res = new ArrayList<>();
        for(User user:userList) {
            res.add(user.getUserId());
        }
        return res;
    }

    /**
     * 获取我的粉丝
     * @param userId
     * @return List<Integer>
     * @Author: 朱佳睿
     * @Time: 2023.03.29
     */
    @Override
    public List<Integer> getAllFollwers(Integer userId)  //获取我的粉丝
    {
        List<User> userList = userRespository.getAllFollowers(userId);
        List<Integer> res = new ArrayList<>();
        for(User user:userList) {
            res.add(user.getUserId());
        }
        return res;
    }

    /**
     * 推荐感兴趣的人
     * @param userId
     * @return
     * @Author: 朱佳睿
     * @Time: 2023.04.04
     */
    @Override
    public List<Integer> recommendUsers(Integer userId){
        Integer limit = 4;  //推荐数量
        List<User> userList = userRespository.recommendUsers(userId, limit);
        List<Integer> res = new ArrayList<>();
        for(User user:userList) {
            res.add(user.getUserId());
        }
        return res;
    }
}
