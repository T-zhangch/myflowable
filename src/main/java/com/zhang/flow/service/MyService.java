package com.zhang.flow.service;

import org.flowable.engine.ProcessEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sun.tools.jar.Main;

import javax.annotation.Resource;
import java.nio.charset.Charset;
import java.util.Arrays;

@Service
public class MyService {

    @Resource
    private ProcessEngine processEngine;

    public void getBean(){
        System.out.println(processEngine);
    }






}
