package com.pw.modular.sys.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.pw.api.sys.entity.SysRole;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface SysRoleMapper extends BaseMapper<SysRole> {

    @Select("select t1.* from pw_sys_role as t1 join pw_sys_user_role as t2 where t2.user_id = #{userId}")
    List<SysRole> fetchRoleByUserId(@Param("userId") Long userId);

}
