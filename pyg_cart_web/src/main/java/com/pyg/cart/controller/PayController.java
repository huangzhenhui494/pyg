package com.pyg.cart.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.pyg.pay.service.WeixinPayService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/pay")
public class PayController {

    // 注入支付service接口对象
    @Reference
    private WeixinPayService weixinPayService;
    /**
     * 需求:生成本地支付二维码
     */
    @RequestMapping("/createNative")
    public Map createNative(){
        Map map = weixinPayService.createNative("1111111111", "0.01");
        return map;
    }
}
