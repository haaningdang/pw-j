package com.pw.login.pojo;

import lombok.Data;

import java.util.List;

@Data
public class PwLogin {

    private Long id;

    private String account;

    private String token;

    private List<Long> role;

}
