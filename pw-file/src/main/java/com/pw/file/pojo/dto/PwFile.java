package com.pw.file.pojo.dto;

import lombok.Data;

@Data
public class PwFile {

    /**
     * 原文件名称
     */
    private String origin;

    /**
     * 原文件后缀
     */
    private String suffix;

    /**
     * 新文件名称
     */
    private String name;

    /**
     * 相对路径
     */
    private String relative;

    /**
     * 相对路径前缀
     */
    private String prefix;

    /**
     * 文件大小（KB）
     */
    private long size;

}
