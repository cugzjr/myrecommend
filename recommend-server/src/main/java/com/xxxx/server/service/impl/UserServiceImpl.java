package com.xxxx.server.service.impl;

import com.xxxx.server.dao.UserRespository;
import com.xxxx.server.pojo.RespBean;
import com.xxxx.server.pojo.User;
import com.xxxx.server.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRespository userRespository;

    @Override
    public RespBean register(Integer userId, String name)
    {
        User user = new User();
        user.setUserId(userId);
        user.setName(name);
        userRespository.save(user);
        return RespBean.success("注册成功");
    }

    @Override
    public RespBean changeName(Integer userId, String name)
    {
        User user = userRespository.findByUserId(userId);
        user.setName(name);
        User newuser = userRespository.updateByNode(user);
        if(newuser != null) return RespBean.success("修改成功");
        else return RespBean.error("修改失败");
    }
}
