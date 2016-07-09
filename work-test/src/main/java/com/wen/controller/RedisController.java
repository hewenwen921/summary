package com.wen.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by hcw on 16-4-14.
 */
@Controller
@RequestMapping("/redis")
public class RedisController {

//    @Autowired
//    RedisTemplate redisTemplate;

    @RequestMapping("/dd")
    public void dd(HttpServletRequest request) {
//        Object d = request.getSession().getAttribute("userName");
//        redisTemplate.opsForValue().set("king", "8998");
//        Object cacheData = redisTemplate.opsForValue().get("king");
//        System.out.println(cacheData);
        System.out.println("-----------");
    }
}
