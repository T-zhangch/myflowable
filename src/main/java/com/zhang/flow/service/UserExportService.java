package com.zhang.flow.service;

import com.baomidou.mybatisplus.extension.service.IService;


public interface UserExportService extends IService<User> {

     void export();

     default void test(){
        System.out.println("122");
    }


}
