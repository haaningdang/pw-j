package com.pw.modular.sys.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.pw.api.sys.entity.SysUserRole;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;

public interface SysUserRoleMapper extends BaseMapper<SysUserRole> {

    @Delete("delete from pw_sys_user_role where role_id = #{roleId}")
    int deleteByRoleId(@Param("roleId") Long roleId);

    @Delete("delete from pw_sys_user_role where user_id = #{userId}")
    int deleteByUserId(@Param("userId") Long userId);

}
