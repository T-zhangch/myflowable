package com.zhang.flow.listener;

import com.alibaba.fastjson.JSON;
import com.zhang.flow.event.UserEvent;
import com.zhang.flow.service.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;

@Slf4j
@Configuration
public class AddUserListener {

    @Async
    @EventListener
    public void account(UserEvent<User> event){
        log.info("账户中心添加操作{}", JSON.toJSONString(event.getData()));
    }

    @Async
    @EventListener
    public void marketing(UserEvent<User> event){
        log.info("营销中心添加{}", JSON.toJSONString(event.getData()));
    }

}
