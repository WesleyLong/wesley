package com.example.demo.rabbitmq;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AmqpTest {

    @Resource
    private AmqpSender sender;

    @Test
    public void testSimpleSend() {
        for (int i = 1; i < 6; i++) {
            this.sender.simpleSend("test simpleSend " + i);
        }
    }

    @Test
    public void testPsSend() {
        for (int i = 1; i < 6; i++) {
            this.sender.psSend("test psSend " + i);
        }
    }

    @Test
    public void testRoutingSend() {
        for (int i = 1; i < 6; i++) {
            this.sender.routingSend("order", "test routingSend " + i);
        }
    }

    @Test
    public void testTopicSend() {
        for (int i = 1; i < 6; i++) {
            this.sender.topicSend("user.add", "test topicSend " + i);
        }
    }
}
