package com.wen.test;

import com.sun.org.apache.xalan.internal.lib.ExsltBase;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.redis.core.RedisTemplate;

/**
 *
 */
public class RedisTest {
    public static void main(String[] args) {
        try {
            ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
            RedisTemplate redisTemplate = (RedisTemplate) ctx.getBean("redisTemplate");
            System.out.println(redisTemplate);

//            redisTemplate.opsForValue().set("king", "12346798");
            Object cacheData = redisTemplate.opsForValue().get("king");
            System.out.println(cacheData);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
