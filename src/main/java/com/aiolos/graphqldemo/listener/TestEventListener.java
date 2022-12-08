package com.aiolos.graphqldemo.listener;

import com.aiolos.graphqldemo.event.TestEvent;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Log4j2
@Component
public class TestEventListener implements ApplicationListener<TestEvent> {

    /*@Async
    @EventListener
    public void listener(TestEvent event) throws InterruptedException {
        log.info("监听到数据：{}", event.getMessage());
    }
*/
    @Override
    @Async
    public void onApplicationEvent(TestEvent event) {
        try {
            Thread.sleep(5000);
        }catch (Exception e){
            e.printStackTrace();
        }
        log.info("onApplicationEvent监听到数据：{}", event.getMessage());
    }
}

