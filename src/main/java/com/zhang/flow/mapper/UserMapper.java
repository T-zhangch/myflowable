package com.zhang.flow.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zhang.flow.service.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<User> {

}
