package com.pw.api.sys.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@Data
@TableName(value = "pw_sys_user")
public class SysUser {

    @TableId(value = "id")
    private Long id;

    @TableField(value = "account")
    private String account;

    @TableField(value = "phone")
    private String phone;

    @TableField(value = "phone")
    private String name;

    @TableField(value = "password")
    private String password;

    @TableField(value = "salt")
    private String salt;

    @TableField(value = "flag")
    private int flag;

    @TableField(value = "create_time")
    private Date createTime;

}
