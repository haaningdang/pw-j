package com.pw.api.auth.pojo.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PwPassword {

    private String password;

    private String crypto;

    private String salt;

}
