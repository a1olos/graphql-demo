package com.aiolos.graphqldemo.controller;

import com.aiolos.graphqldemo.service.PublishEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EventController {

    @Autowired
    PublishEventService publishService;

    @GetMapping("/v1/event")
    public String index(){
        for (int i = 0; i < 5; i++) {
            publishService.publish("消息" + (i + 1));
        }
        return  "event";
    }
    
}
