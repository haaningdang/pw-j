package com.pw.modular.sys.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.pw.api.sys.entity.SysResource;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface SysResourceMapper extends BaseMapper<SysResource> {

    @Select("<script>" +
            "   select " +
            "       t1.* " +
            "   from pw_sys_resource as t1 " +
            "   join pw_sys_role_resource as t2 on t1.id = t2.res_id " +
            "   where " +
            "       t2.role_id in " +
            "   <foreach collection=\"roles\" item=\"item\" open=\"(\" close=\")\" separator=\",\">" +
            "       #{item}" +
            "   </foreach>" +
            "   and t1.flag = 1" +
            "   order by t1.sort asc, t1.create_time desc " +
            "</script>")
    List<SysResource> fetchResourceByRoleId(@Param("roles") List<String> roles);

}
