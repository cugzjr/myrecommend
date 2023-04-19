package com.xxxx.server;

import com.xxxx.server.dao.BuyRespository;
import com.xxxx.server.dao.FocusRespository;
import com.xxxx.server.dao.ProductRespository;
import com.xxxx.server.dao.UserRespository;
import com.xxxx.server.pojo.Product;
import com.xxxx.server.pojo.User;
import com.xxxx.server.relation.BuyRelation;
import com.xxxx.server.service.FocusService;
import io.swagger.models.auth.In;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

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
    @Autowired
    FocusRespository focusRespository;
    @Autowired
    FocusService focusService;

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
        if(user1 != null) {
            System.out.println(1);
        }
        else{
            System.out.println(0);
        }
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
        List<User> userList = userRespository.recommendUsers(5, 4);
        for(User user:userList){
            System.out.print(user.getName());
            System.out.print("\n");
        }
    }

    /**
     * 获取相似度评分列表测试
     */
    @Test
    public void getSimilarity(){
        User user = userRespository.findByUserId(11);
        System.out.print(user.getName());
        List<Map<String, Object>> resultList = userRespository.recommend(11, 4);
        resultList.forEach(result -> {
            System.out.println("User: " + result.get("other") + ", similarity: " + result.get("similarity") + ", followers: " + result.get("followers") + ", weightedScore: " + result.get("weightedScore"));
        });
    }

    /**
     * 删除关注关系
     */
    @Test
    public void testDeleteFocus(){
        focusService.deleteFocus(2, 1);
    }

    @Test
    public void insertData() throws IOException {
        List<Integer> productIdList = new ArrayList<>();
        // 创建 reader
        try (BufferedReader br = Files.newBufferedReader(Paths.get("../docs/data/myproducts.csv"))) {
            // CSV文件的分隔符
            String DELIMITER = ",";
            // 按行读取
            String line;
            while ((line = br.readLine()) != null) {
                // 分割
                String[] columns = line.split(DELIMITER);
//                Product product = new Product();
//                product.setProductId(Integer.valueOf(columns[0]));
//                product.setName(columns[1]);
//                productRespository.save(product);

                productIdList.add(Integer.valueOf(columns[0]));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        File writeFile = new File("../docs/data/ratings.csv");
        Map<Integer, List<Integer>> mymap = new HashMap<>();
        try{
            //第二步：通过BufferedReader类创建一个使用默认大小输出缓冲区的缓冲字符输出流
            BufferedWriter writeText = new BufferedWriter(new FileWriter(writeFile));

            Integer minnum1 = 1, maxnum1 = 2000;
            Integer minnum2 = 0, maxnum2 = productIdList.size() - 1;
            for(int i = 0; i < 10000; i ++){
                Random random = new Random();
                Integer randomUserId = random.nextInt(maxnum1 - minnum1 + 1) + minnum1;
                User randomUser = userRespository.findByUserId(randomUserId);
                if(randomUser != null) {
                    int idx = random.nextInt(maxnum2 - minnum2 + 1) + minnum2;
                    Integer randomProductId = productIdList.get(idx);
                    Product product = productRespository.findByProductId(randomProductId);
                    if(product != null) {
                        Integer score = random.nextInt(6);
                        if(mymap.containsKey(randomUserId)){
                            List<Integer> tempList = mymap.get(randomUserId);
                            if(!tempList.contains(randomProductId)) {
                                List<Integer> mylist = mymap.get(randomUserId);
                                mylist.add(randomProductId);
                                mymap.put(randomUserId, mylist);
                                Integer time = Math.toIntExact(System.currentTimeMillis() / 1000);
                                writeText.newLine();    //换行
                                writeText.write(String.valueOf(randomUserId) + "," + String.valueOf(randomProductId)
                                + "," + String.valueOf(score) + "," + String.valueOf(time));
                            }
                        }
                        else{
                            List<Integer> mylist = new ArrayList<>();
                            mylist.add(randomProductId);
                            mymap.put(randomUserId, mylist);
                            Integer time = Math.toIntExact(System.currentTimeMillis() / 1000);
                            writeText.newLine();    //换行
                            writeText.write(String.valueOf(randomUserId) + "," + String.valueOf(randomProductId)
                                    + "," + String.valueOf(score) + "," + String.valueOf(time));
                        }
                    }
                }
            }
            //使用缓冲区的刷新方法将数据刷到目的地中
            writeText.flush();
            //关闭缓冲区，缓冲区没有调用系统底层资源，真正调用底层资源的是FileWriter对象，缓冲区仅仅是一个提高效率的作用
            //因此，此处的close()方法关闭的是被缓存的流对象
            writeText.close();
        }catch (FileNotFoundException e){
            System.out.println("没有找到指定文件");
        }catch (IOException e){
            System.out.println("文件读写出错");
        }
    }

}
