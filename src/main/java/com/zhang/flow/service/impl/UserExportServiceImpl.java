package com.zhang.flow.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhang.flow.mapper.UserMapper;
import com.zhang.flow.service.User;
import com.zhang.flow.service.UserExportService;
import org.springframework.stereotype.Service;

@Service
public class UserExportServiceImpl extends ServiceImpl<UserMapper, User> implements UserExportService {


    @Override
    public void export() {
        new UserExportServiceImpl().test();
    }

}
