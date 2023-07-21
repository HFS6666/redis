package edu.wtbu.springboootredis.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/redisTest")
public class RedistestController {

    @Autowired
    RedisTemplate redisTemplate;
    @GetMapping
    public String testredis(){
        redisTemplate.opsForValue().set("xm","男");
        String gender=(String) redisTemplate.opsForValue().get("xm");
        return gender;
    }

}
