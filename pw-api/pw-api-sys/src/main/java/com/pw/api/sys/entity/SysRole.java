package com.pw.api.sys.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@Data
@TableName(value = "pw_sys_role")
public class SysRole {

    @TableId(value = "id")
    private Long id;

    @TableField(value = "role_name")
    private String roleName;

    @TableField(value = "role_code")
    private String roleCode;

    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private Date createTime;

}
