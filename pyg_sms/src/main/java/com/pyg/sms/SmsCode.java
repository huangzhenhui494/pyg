package com.pyg.sms;

import com.alibaba.fastjson.JSON;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.pyg.utils.SmsUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class SmsCode {

    @Autowired
    private Environment env;

    /**
     * 监听消息
     *  1.手机号
     *  2.签名
     *  根据消息发送短信:
     *  调用阿里云工具类发送短信
     * 使用队列,点对点模式
     */
    @JmsListener(destination = "smsQueue")
    public void sendSms(String message){

        try {
            // 把字符串转换成对象
            Map mapMessage = JSON.parseObject(message,Map.class);
            // 获取手机号
            String mobile = (String) mapMessage.get("mobile");
            // 获取签名
            String signName = (String) mapMessage.get("signName");
            // 获取验证码
            String code = (String) mapMessage.get("number");
            // 获取accessKeyId
            String accessKeyId = env.getProperty("accessKeyId");
            // 获取AccessKeySecret
            String accessKeySecret = env.getProperty("accessKeySecret");
            // 获取template_code
            String templateCode = env.getProperty("template_code");

            // 调用工具类方法发短信
            SendSmsResponse response = SmsUtils.sendSms(mobile, signName, templateCode, code, accessKeyId, accessKeySecret);

            System.out.println("短信接口返回的数据----------------");
            System.out.println("Code=" + response.getCode());
            System.out.println("Message=" + response.getMessage());
            System.out.println("RequestId=" + response.getRequestId());
            System.out.println("BizId=" + response.getBizId());

            Thread.sleep(3000L);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
