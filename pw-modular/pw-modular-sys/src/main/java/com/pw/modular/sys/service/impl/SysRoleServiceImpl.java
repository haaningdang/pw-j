package com.pw.modular.sys.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pw.api.sys.entity.SysRole;
import com.pw.api.sys.pojo.request.role.PageRequest;
import com.pw.api.sys.pojo.request.role.RoleRequest;
import com.pw.api.sys.pojo.request.role.RoleResourceRequest;
import com.pw.api.sys.service.SysRoleResourceService;
import com.pw.api.sys.service.SysRoleService;
import com.pw.api.sys.service.SysUserRoleService;
import com.pw.core.basic.response.PwResponse;
import com.pw.mbp.page.PwPageUtil;
import com.pw.modular.sys.mapper.SysRoleMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements SysRoleService {

    @Resource
    private SysUserRoleService sysUserRoleService;

    @Resource
    private SysRoleResourceService sysRoleResourceService;

    /**
     * 构造编码查询wrapper
     * @param code
     * @return
     */
    private LambdaQueryWrapper<SysRole> createWrapperByCode(String code) {
        return new LambdaQueryWrapper<SysRole>().eq(SysRole::getRoleCode, code);
    }

    /**
     * 通过编码查询列表
     * @param code
     * @return
     */
    private List<SysRole> fetchByCode(String code) {
        return this.list(createWrapperByCode(code));
    }

    @Override
    public List<SysRole> fetchRoleByUserId(Long userId) {
        return this.baseMapper.fetchRoleByUserId(userId);
    }

    @Override
    public PwResponse page(PageRequest request) {
        Page<SysRole> page = this.baseMapper.page(PwPageUtil.wrapper(request), request);
        return PwResponse.success(PwPageUtil.response(page));
    }

    @Override
    public PwResponse add(RoleRequest request) {
        // 判断角色编码是否存在
        List<SysRole> roles = this.fetchByCode(request.getRoleCode());
        if(CollUtil.isNotEmpty(roles)) {
            return PwResponse.failure("角色编码已经存在");
        }

        SysRole sysRole = new SysRole();
        sysRole.setRoleCode(request.getRoleCode());
        sysRole.setRoleName(request.getRoleName());
        this.save(sysRole);

        return PwResponse.success();
    }

    /**
     * 删除角色
     * @param request
     * @return
     */
    @Override
    public PwResponse del(RoleRequest request) {
        this.removeById(request.getId());
        sysUserRoleService.deleteByRoleId(request.getId());

        return PwResponse.success();
    }

    @Override
    public PwResponse select() {
        List<SysRole> roles = this.list();
        return PwResponse.success(roles);
    }

    @Override
    public PwResponse resource(RoleResourceRequest request) {
        // 先删除原来的关联
        sysRoleResourceService.deleteRoleResourceByRoleId(request.getRoleId());

        // 重新添加
        sysRoleResourceService.saveRoleResource(request);
        return PwResponse.success();
    }

}
