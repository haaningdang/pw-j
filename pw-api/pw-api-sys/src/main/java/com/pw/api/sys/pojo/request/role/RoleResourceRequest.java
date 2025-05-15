package com.pw.api.sys.pojo.request.role;

import lombok.Data;

import java.util.List;

@Data
public class RoleResourceRequest {

    Long roleId;

    List<Long> resources;

}
