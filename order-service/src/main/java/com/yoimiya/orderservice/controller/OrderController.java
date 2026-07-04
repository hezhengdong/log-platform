package com.yoimiya.orderservice.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class OrderController {

    @PostMapping("/order")
    public String createOrder() {
        long orderId = System.currentTimeMillis();
        int userId = (int) (Math.random() * 1000);
        double amount = Math.random() * 500 + 10;

        double r = Math.random();
        if (r < 0.7) {
            log.info("订单创建成功 - orderId={} userId={} amount={}", orderId, userId, String.format("%.2f", amount));
            return "ok";
        } else if (r < 0.9) {
            log.warn("订单创建失败 - orderId={} userId={} reason=库存不足", orderId, userId);
            return "fail";
        } else {
            log.error("订单创建异常 - orderId={} userId={} cause=数据库连接超时", orderId, userId);
            return "error";
        }
    }
}
