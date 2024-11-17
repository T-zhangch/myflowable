package com.zhang.flow.service;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;


@Data
@TableName("t_user")
public class User implements Serializable {

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;


    @TableId
    private Long id;

    @TableField("name")
    @NotNull
    private String name;

    @TableField("age")
    @NotNull
    private Integer age;
}
