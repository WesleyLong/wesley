package com.example.demo.annotations;

import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class WesleyAspect {

    private final static Logger LOGGER = LoggerFactory.getLogger(WesleyAspect.class);

    @Pointcut("@annotation(com.example.demo.annotations.Wesley)")
    public void wl() {
    }

    @Before("wl() && @annotation(wesley)")
    public void doBefore(Wesley wesley) {
        String value = wesley.value();
        LOGGER.info("@Wesley before msg: [{}]", value);
        //do something
    }

    @After(value = "wl() && @annotation(wesley)")
    public void testAfter(Wesley wesley) {
        String value = wesley.value();
        LOGGER.info("@Wesley after msg: [{}]", value);
        //do something
    }
}
