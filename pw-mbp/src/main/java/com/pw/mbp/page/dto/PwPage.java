package com.pw.mbp.page.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class PwPage<T> implements Serializable {

    // 当前页码
    private int current;

    // 每页限制数量
    private int limit;

    // 页数
    private int page;

    // 数据总量
    private int total;

    // 数据
    private List<T> rows;

}
