package com.pw.modular.sys.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pw.api.sys.entity.SysUser;
import com.pw.api.sys.pojo.request.user.PageRequest;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface SysUserMapper extends BaseMapper<SysUser> {

    @Select("select * from pw_sys_user")
    List<SysUser> fetch();

    @Select("<script>" +
            "select " +
            "   id," +
            "   account," +
            "   phone," +
            "   name," +
            "   '' as password," +
            "   '' as salt," +
            "   flag," +
            "   create_time " +
            "from pw_sys_user as t1 " +
            "<where>" +
            "   <if test=\"request.keyword != null and request.keyword != ''\">" +
            "       and (" +
            "           t1.name like concat('%', #{request.keyword}, '%') " +
            "           or t1.account like concat('%', #{request.keyword}, '%')" +
            "       )" +
            "   </if>" +
            "</where>" +
            "order by t1.create_time desc" +
            "</script>")
    Page<SysUser> page(@Param("page") Page page, @Param("request") PageRequest request);

    @Update("update pw_sys_user set salt = #{salt}, password = #{password} where id = #{id}")
    int updatePassword(@Param("id") Long id, @Param("salt") String salt, @Param("password") String password);

}
