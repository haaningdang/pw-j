package com.pw.api.sys.pojo.dto.resource;

import lombok.Data;

import java.util.List;

@Data
public class ResourceSelectTree {

    public Long id;

    public String title;

    public String field;

    private List<ResourceSelectTree> children;

}
