package com.pw.api.sys.pojo.request.resource;

import lombok.Data;

@Data
public class ResourceRequest {

    private Long roleId;

    private Long parent;

    private Long id;

    private String resCode;

    private String resName;

    private Integer resType;

    private Integer flag;

    private String resUrl;

    private String icon;

}
