package com.xxxx.server;

import com.xxxx.server.dao.BuyRespository;
import com.xxxx.server.dao.ProductRespository;
import com.xxxx.server.dao.UserRespository;
import com.xxxx.server.pojo.Product;
import com.xxxx.server.pojo.User;
import com.xxxx.server.relation.BuyRelation;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.List;
import java.util.Map;

/**
 * 图数据库操作测试
 * @Author: 朱佳睿
 * @Time: 2023.03.28
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class recommendtest {

    @Autowired
    BuyRespository buyRespository;
    @Autowired
    UserRespository userRespository;

    @Autowired
    ProductRespository productRespository;

    /**
     * 创建实体
     * @Author: 朱佳睿
     * @Time: 2023.03.28
     */
    @Test
    public void createPojo(){
        Product product = productRespository.findByProductId(4);
        System.out.print(product.getName());
    }

    /**
     * 创建关系
     * @Author: 朱佳睿
     * @Time: 2023.03.28
     */
    @Test
    public void createRelation(){
        List<User> userList = userRespository.recommendUsers(6, 4);
        System.out.println(userList.size());
        for(User user:userList){
            System.out.print(user.getName());
            System.out.print("\n");
        }

    }

    /**
     * 更新节点
     * @Author: 朱佳睿
     * @Time: 2023.03.28
     */
    @Test
    public void update(){
        User user = userRespository.findByUserId(2);
        user.setName("zjr");
        User user1 = userRespository.updateByNode(user);
        if(user1 != null) System.out.println(1);
        else System.out.println(0);
    }

    /**
     * 获取关注数
     * @Author: 朱佳睿
     * @Time: 2023.03.28
     */
    @Test
    public void focus(){
        List<User> userList = userRespository.getAllFollowers(11);
        for(User user:userList){
            System.out.print(user.getName());
        }
    }

    /**
     * 推荐感兴趣的人测试
     * @Author: 朱佳睿
     * @Time: 2023.04.04
     */
    @Test
    public void recommend(){
        List<User> userList = userRespository.recommendUsers(6, 4);
        for(User user:userList){
            System.out.print(user.getName());
            System.out.print("\n");
        }
    }


}
