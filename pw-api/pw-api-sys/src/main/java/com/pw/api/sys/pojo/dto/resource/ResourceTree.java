package com.pw.api.sys.pojo.dto.resource;

import lombok.Data;

import java.util.List;

@Data
public class ResourceTree {

    private Long id;

    private String title;

    private String icon;

    private boolean checked = false;

    private boolean spread = false;

    List<ResourceTree> children;

}
