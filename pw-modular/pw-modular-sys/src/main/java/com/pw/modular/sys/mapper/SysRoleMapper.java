package com.pw.modular.sys.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pw.api.sys.entity.SysRole;
import com.pw.api.sys.pojo.request.role.PageRequest;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface SysRoleMapper extends BaseMapper<SysRole> {

    @Select("select " +
            "   t1.* " +
            "from pw_sys_role as t1 " +
            "join pw_sys_user_role as t2 on t1.id = t2.role_id " +
            "where t2.user_id = #{userId}")
    List<SysRole> fetchRoleByUserId(@Param("userId") Long userId);

    @Select("<script>" +
            "select " +
            "   t1.* " +
            "from pw_sys_role as t1 " +
            "<where>" +
            "   <if test=\"request.keyword != null and request.keyword != ''\">" +
            "       and (" +
            "           t1.role_name like concat('%', #{request.keyword}, '%') " +
            "           or t1.role_code like concat('%', #{request.keyword}, '%')" +
            "       )" +
            "   </if>" +
            "</where>" +
            "</script>")
    Page<SysRole> page(@Param("page") Page page, @Param("request") PageRequest request);

}
