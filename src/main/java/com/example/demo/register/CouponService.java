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
public class CouponService {
    /**
     * 监听用户注册事件,执行发放优惠券逻辑
     */
    @EventListener
    public void addCoupon(Events.UserRegisterEvent event) {
        log.info("给用户[{}]发放优惠券", event.getUsername());
    }
}
