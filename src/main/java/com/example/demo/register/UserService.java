package com.example.demo.register;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserService implements ApplicationEventPublisherAware {

    // 注入事件发布者
    private ApplicationEventPublisher applicationEventPublisher;

    @Override
    public void setApplicationEventPublisher(@NonNull ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }

    /**
     * 发布事件
     */
    public void register(String username) {
        log.info("执行用户[{}]的注册逻辑", username);
        applicationEventPublisher.publishEvent(new Events.UserRegisterEvent(this, username));
    }

    public void logout(String username) {
        log.info("执行用户[{}]的登出逻辑", username);
        applicationEventPublisher.publishEvent(new Events.UserLogoutEvent(this, username));
    }
}
