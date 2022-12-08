package com.aiolos.graphqldemo.listener;

import com.aiolos.graphqldemo.event.OtherEvent;
import com.aiolos.graphqldemo.event.TestEvent;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Log4j2
@Component
public class OtherEventListener{

    @Async
    @EventListener
    public void listener(OtherEvent event) throws InterruptedException {
        log.info("OtherEventListener监听到数据：{}", event.getMessage());
    }


}

