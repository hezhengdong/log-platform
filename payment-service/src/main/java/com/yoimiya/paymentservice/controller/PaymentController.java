package com.yoimiya.paymentservice.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class PaymentController {

    @PostMapping("/pay")
    public String pay() {
        long orderId = System.currentTimeMillis();
        int userId = (int) (Math.random() * 1000);
        double amount = Math.random() * 500 + 10;

        double r = Math.random();
        if (r < 0.7) {
            log.info("支付成功 - orderId={} userId={} amount={}", orderId, userId, String.format("%.2f", amount));
            return "ok";
        } else if (r < 0.9) {
            log.warn("支付失败 - orderId={} userId={} reason=余额不足", orderId, userId);
            return "fail";
        } else {
            log.error("支付异常 - orderId={} userId={} cause=支付网关超时", orderId, userId);
            return "error";
        }
    }
}
