package com.aiolos.graphqldemo.service;

import com.aiolos.graphqldemo.event.OtherEvent;
import com.aiolos.graphqldemo.event.TestEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ListenerService {

    @EventListener
    //异步监听
    @Async
    public void listener(TestEvent event)  throws InterruptedException {
        Thread.sleep(5000);
        log.info("ListenerService监听到数据：{}", event.getMessage());
    }

    @EventListener
    //异步监听
    @Async
    public void listener(OtherEvent event)  throws InterruptedException {
        Thread.sleep(5000);
        log.info("OtherEventService监听到数据：{}", event.getMessage());
    }
}
