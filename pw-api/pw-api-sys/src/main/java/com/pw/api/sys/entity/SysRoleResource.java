package com.pw.api.sys.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName(value = "pw_sys_role_resource")
public class SysRoleResource {

    @TableId(value = "id")
    private Long id;

    @TableField(value = "res_id")
    private Long resId;

    @TableField(value = "role_id")
    private Long roleId;

}
