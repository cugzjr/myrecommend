package com.xxxx.server;

import com.xxxx.server.dao.FocusRespository;
import com.xxxx.server.dao.ProductRespository;
import com.xxxx.server.dao.UserRespository;
import com.xxxx.server.pojo.Product;
import com.xxxx.server.pojo.User;
import com.xxxx.server.relation.FocusRelation;
import io.swagger.models.auth.In;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.jws.soap.SOAPBinding;
import java.util.Random;

@SpringBootTest
@RunWith(SpringRunner.class)
public class AddTest {

    @Autowired
    private UserRespository userRespository;
    @Autowired
    private FocusRespository focusRespository;

    @Autowired
    private ProductRespository productRespository;

    @Test
    public void Test(){
        int total = userRespository.getAllUserCount();  //总人数
        while(focusRespository.getAllPeopleFocus() <= total/2 && focusRespository.getAllPeopleFellows() < total/2)
        {
            int minnum = 20;
            int maxnum = 2000;

            Random random = new Random();
            Integer randomNum1 = random.nextInt(maxnum - minnum + 1) + minnum;
            User user1 = userRespository.findByUserId(randomNum1);
            if(user1 != null) {
                Integer randomNum2;
                do {
                    randomNum2 = random.nextInt(maxnum - minnum + 1) + minnum;
                } while (randomNum2 == randomNum1);
                User user2 = userRespository.findByUserId(randomNum2);
                if(user2 != null)
                {
                    FocusRelation focusRelation = FocusRelation.builder().startUser(user1).endUser(user2).relation("关注").build();
                    focusRespository.save(focusRelation);
                }

            }
        }
    }

    @Test
    public void testTime(){
        Product product = productRespository.findByProductId(640);
        if(product != null){
            System.out.print(product.getName());
        }
    }
}
