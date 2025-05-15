package com.pw.modular.sys.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.convert.Convert;
import cn.hutool.core.text.CharSequenceUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pw.api.sys.entity.SysRole;
import com.pw.api.sys.entity.SysUser;
import com.pw.api.sys.entity.SysUserRole;
import com.pw.api.sys.pojo.request.user.PageRequest;
import com.pw.api.sys.pojo.request.user.UserRequest;
import com.pw.api.sys.service.SysUserRoleService;
import com.pw.api.sys.service.SysUserService;
import com.pw.core.basic.response.PwResponse;
import com.pw.core.pojo.password.PwPassword;
import com.pw.core.util.PasswordUtil;
import com.pw.mbp.page.PwPageUtil;
import com.pw.modular.sys.mapper.SysUserMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Slf4j
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {

    @Resource
    private SysUserRoleService sysUserRoleService;

    @Override
    public List<SysUser> fetchByWrapper(LambdaQueryWrapper<SysUser> wrapper) {
        return this.list(wrapper);
    }

    private LambdaQueryWrapper<SysUser> createWrapperFetchSysUser(Long id) {
        return new LambdaQueryWrapper<SysUser>().eq(SysUser::getId, id);
    }

    @Override
    public List<SysUser> fetchSysUserByUserId(Long userId) {
        return fetchByWrapper(createWrapperFetchSysUser(userId));
    }

    private LambdaQueryWrapper<SysUser> createWrapperFetchSysUserByAccount(String account) {
        return new LambdaQueryWrapper<SysUser>().eq(SysUser::getAccount, account);
    }

    @Override
    public List<SysUser> fetchSysUserByAccount(String account) {
        return fetchByWrapper(createWrapperFetchSysUserByAccount(account));
    }

    @Override
    public PwResponse page(PageRequest request) {
        Page<SysUser> page = this.baseMapper.page(PwPageUtil.wrapper(request), request);
        return PwResponse.success(PwPageUtil.response(page));
    }

    @Override
    public PwResponse add(UserRequest request) {
        // 校验账号是否存在
        List<SysUser> users = fetchSysUserByAccount(request.getAccount());
        if(CollUtil.isNotEmpty(users)){
            return PwResponse.failure("用户账号已经存在！");
        }

        SysUser user = new SysUser();
        BeanUtils.copyProperties(request, user);

        PwPassword password = PasswordUtil.generate(request.getPassword());
        user.setPassword(password.getCrypto());
        user.setSalt(password.getSalt());

        this.save(user);

        // 添加用户角色
        String requestRoles = request.getRoles();
        String[] roles = requestRoles.split(",");
        for (String role : roles) {
            try {
                SysUserRole sysUserRole = new SysUserRole();
                sysUserRole.setUserId(user.getId());
                sysUserRole.setRoleId(Convert.toLong(role));
                sysUserRoleService.saveUserRole(sysUserRole);
            } catch (Exception e) {
                log.error(e.getMessage());
            }
        }

        return PwResponse.success();
    }

    @Override
    public PwResponse del(UserRequest request) {
        this.removeById(request.getId());
        sysUserRoleService.deleteByUserId(request.getId());

        return PwResponse.success();
    }

    @Override
    public PwResponse reset(UserRequest request) {
        List<SysUser> users = fetchSysUserByUserId(request.getId());
        if(CollUtil.isEmpty(users)){
            return PwResponse.failure("用户不存在！");
        }

        // 比较两次密码是否正确
        String password = request.getPassword();
        String repeat = request.getRepeat();
        if(!CharSequenceUtil.equals(password, repeat)){
            return PwResponse.failure("两次输入密码不一致");
        }

        // 修改密码
        PwPassword pass = PasswordUtil.generate(password);
        this.baseMapper.updatePassword(request.getId(), pass.getSalt() , pass.getPassword());

        return PwResponse.success();
    }
}
