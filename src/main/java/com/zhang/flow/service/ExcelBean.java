package com.zhang.flow.service;


import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

@Data
public class ExcelBean {

    @ExcelProperty
    private int id;

    @ExcelProperty
    private String name;

    @ExcelProperty
    private int age;


}
