package com.ricky.core.controller;

import com.netflix.discovery.converters.Auto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ningj
 * @version 1.0
 * @date 2020/5/27
 * @description
 */
@RestController
public class DemoCoreController {

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private KafkaTemplate kafkaTemplate;

    @GetMapping("/redis/{key}/{hashKey}/{value}")
    public String redisMapping(@PathVariable String key, @PathVariable String hashKey, @PathVariable String value){

        redisTemplate.opsForValue().set(key,value);

        return redisTemplate.opsForValue().get(key).toString();

    }

    @GetMapping("/kafka/{topic}/{message}")
    public String kafkaMessage(@PathVariable String topic,@PathVariable String message){

        kafkaTemplate.send(topic,message);

        return "kafka-ok";
    }

    @GetMapping("demo")
    public String demo(){
        return "get mapping ok";
    }

}
