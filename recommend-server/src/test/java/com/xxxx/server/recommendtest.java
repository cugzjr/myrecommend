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

@SpringBootTest
@RunWith(SpringRunner.class)
public class recommendtest {

    @Autowired
    BuyRespository buyRespository;
    @Autowired
    UserRespository userRespository;

    @Autowired
    ProductRespository productRespository;

    @Test
    public void createPojo(){
        Product product = productRespository.findByProductId(4);
        System.out.print(product.getName());
    }
    @Test
    public void createRelation(){
        User user = new User();
        user.setUserId(2);
        user.setName("朱佳睿");
        Product product = new Product();
        product.setProductId(1);
        product.setName("薯片");
        userRespository.save(user);
        productRespository.save(product);
        BuyRelation buyRelation = BuyRelation.builder().user(user).product(product).relation("购买").build();
        buyRespository.save(buyRelation);
    }

    @Test
    public void createNew(){
        User user = userRespository.findByUserId(2);
        Product product = new Product();
        product.setProductId(4);
        product.setName("电脑");
        productRespository.save(product);
        BuyRelation buyRelation = BuyRelation.builder().user(user).product(product).relation("购买").build();
        buyRespository.save(buyRelation);
    }

    @Test
    public void update(){
        User user = userRespository.findByUserId(2);
        user.setName("zjr");
        User user1 = userRespository.updateByNode(user);
        if(user1 != null) System.out.println(1);
        else System.out.println(0);
    }
}
