package com.pw.api.sys.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@Data
@TableName(value = "pw_sys_resource")
public class SysResource {

    @TableId(value = "id")
    private Long id;

    @TableField(value = "parent_id")
    private Long parentId;

    @TableField(value = "res_code")
    private String resCode;

    @TableField(value = "res_name")
    private String resName;

    @TableField(value = "res_url")
    private String resUrl;

    @TableField(value = "icon")
    private String icon;

    @TableField(value = "res_type")
    private int resType;

    @TableField(value = "flag")
    private int flag;

    @TableField(value = "sort")
    private int sort;

    @TableField(value = "create_time",  fill = FieldFill.INSERT)
    private Date createTime;


}
