package com.pw.api.sys.pojo.request.user;

import lombok.Data;

@Data
public class UserRequest {

    private Long id;

    private String account;

    private String phone;

    private String name;

    private String password;

    private String repeat;

    private Integer flag;

    private String roles;

}
