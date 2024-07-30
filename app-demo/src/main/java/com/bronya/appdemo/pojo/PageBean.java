package com.bronya.appdemo.pojo;

import lombok.Data;

import java.util.List;

@Data
public class PageBean<T> {
    private long total;
    private List<T> rows;
}
