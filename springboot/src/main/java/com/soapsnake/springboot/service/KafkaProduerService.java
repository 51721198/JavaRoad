package com.soapsnake.springboot.service;


import com.alibaba.fastjson.JSON;
import com.soapsnake.springboot.domain.KafkaMessage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.UUID;

@Service
public class KafkaProduerService {

    @Value("${kafka.topic}")
    private String kafkaTopic;

    @Resource
    private KafkaTemplate<String, String> kafkaTemplate;

    public String send() {
        KafkaMessage message = new KafkaMessage();
        message.setId(System.currentTimeMillis());
        message.setMsg(UUID.randomUUID().toString());
        message.setSendTime(new Date());
        kafkaTemplate.send(kafkaTopic, JSON.toJSONString(message));
        return JSON.toJSONString(message);
    }
}
