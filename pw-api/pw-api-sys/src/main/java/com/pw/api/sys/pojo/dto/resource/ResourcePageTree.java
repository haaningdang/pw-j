package com.pw.api.sys.pojo.dto.resource;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class ResourcePageTree {

    private Long id;

    private Long parentId;

    private String resCode;

    private String resName;

    private String resUrl;

    private String icon;

    private int resType;

    private int flag;

    private int sort;

    private Date createTime;

    private List<ResourcePageTree> children;

}
