package com.pyg.redis;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Set;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath*:spring/applicationContext-jedis.xml")
public class MySpringDataRedis {

    // 注入springdataredis的模板对象
    @Autowired
    private RedisTemplate redisTemplate;

    // 测试
    // String
    @Test
    public void test01(){
        redisTemplate.boundValueOps("username").set("黄黄黄");
    }

    // 测试2
    // 获取String类型的值
    @Test
    public void test02(){
        String username = (String) redisTemplate.boundValueOps("username").get();
        System.out.println(username);
    }

    // 测试3
    // 删除String类型的值
    @Test
    public void test03(){
        redisTemplate.delete("username");
    }

    // 测试4
    // set数据类型添加操作
    // Set集合
    @Test
    public void test04(){
        // 添加值
        redisTemplate.boundSetOps("user").add("张三");
    }

    // 测试5
    // 获取set集合数据
    @Test
    public void test05(){
        Set user = redisTemplate.boundSetOps("user").members();
        for (Object o : user) {
            System.out.println(o);
        }


    }

    // 测试6
    // 删除set集合数据
    @Test
    public void test06(){
        redisTemplate.delete("user");
    }

    // 测试7
    // 添加list值
    @Test
    public void test07(){
        redisTemplate.boundListOps("mylist").rightPush("我是第一个");
        redisTemplate.boundListOps("mylist").rightPush("我是第二个");
    }

    // 测试8
    // 查询list值
    @Test
    public void test08(){
        String value = (String) redisTemplate.boundListOps("mylist").rightPop();
        System.out.println("从右边出栈"+value);
    }

    // 测试9
    // 添加hash集合数据类型
    @Test
    public void test09(){
        redisTemplate.boundHashOps("itcast").put("a","张飞");
        redisTemplate.boundHashOps("itcast").put("b","关羽");
        redisTemplate.boundHashOps("itcast").put("c","刘备");
    }

    // 测试10
    // 获取hash集合数据类型
    @Test
    public void test10(){
        String b = (String) redisTemplate.boundHashOps("itcast").get("b");
        System.out.println(b);
    }




}
