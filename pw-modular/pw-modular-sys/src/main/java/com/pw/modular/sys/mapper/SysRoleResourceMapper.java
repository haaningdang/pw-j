package com.pw.modular.sys.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.pw.api.sys.entity.SysRoleResource;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;

public interface SysRoleResourceMapper extends BaseMapper<SysRoleResource> {

    @Delete("delete from pw_sys_role_resource where role_id = #{roleId}")
    void deleteRoleResourceByRoleId(@Param("roleId") Long roleId);
}
