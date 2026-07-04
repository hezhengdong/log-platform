package com.yoimiya.userservice.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class UserController {

    @PostMapping("/login")
    public String login() {
        int userId = (int) (Math.random() * 1000);
        String ip = "192.168.1." + (int) (Math.random() * 255);

        double r = Math.random();
        if (r < 0.7) {
            log.info("登录成功 - userId={} ip={}", userId, ip);
            return "ok";
        } else if (r < 0.9) {
            log.warn("登录失败 - userId={} ip={} reason=密码错误", userId, ip);
            return "fail";
        } else {
            log.error("登录异常 - userId={} ip={} cause=Redis连接失败", userId, ip);
            return "error";
        }
    }
}
