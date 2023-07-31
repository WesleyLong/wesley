package com.example.demo.register;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

/**
 * 注解方式 @EventListener
 * @author Summerday
 */
@Service
@Slf4j
public class TestService {
    /**
     * 监听用户注册事件,执行发放优惠券逻辑
     */
    @EventListener
    public void add(Events.UserLogoutEvent event) {
        log.info("用户{}登出", event.getUsername());
    }
}
