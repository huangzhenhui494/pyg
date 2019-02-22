package com.pyg.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
public class SmsController {

    /**
     * 需求:测试短信网关服务:发送消息
     * 队列方式发送消息:queue
     */

    // 注入jms模板对象
    @Autowired
    private JmsTemplate jmsTemplate;

    @RequestMapping("/sendSms")
    public void sendSms(){
        // 创建map对象
        HashMap<String, String> mapMessage = new HashMap<String, String>();
        mapMessage.put("mobile","13174508716");
        mapMessage.put("signName","黑马");
        jmsTemplate.convertAndSend(mapMessage);
    }

}
