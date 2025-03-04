package com.pw.modular.sys.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.pw.api.sys.entity.SysUser;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface SysUserMapper extends BaseMapper<SysUser> {

    @Select("select * from pw_sys_user")
    List<SysUser> fetch();

}
