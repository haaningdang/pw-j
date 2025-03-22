package com.pw.modular.auth.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.convert.Convert;
import cn.hutool.core.map.MapBuilder;
import cn.hutool.core.text.CharSequenceUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson2.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.pw.api.auth.pojo.request.AuthRequest;
import com.pw.api.auth.service.AuthService;
import com.pw.api.auth.util.PasswordUtil;
import com.pw.api.sys.entity.SysResource;
import com.pw.api.sys.entity.SysRole;
import com.pw.api.sys.entity.SysUser;
import com.pw.api.sys.service.SysResourceService;
import com.pw.api.sys.service.SysRoleService;
import com.pw.api.sys.service.SysUserService;
import com.pw.cache.PwCacheApi;
import com.pw.core.basic.response.PwResponse;
import com.pw.core.context.PwApplicationContext;
import com.pw.login.context.PwLoginContext;
import com.pw.login.pojo.PwLogin;
import com.pw.security.util.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Service
public class AuthServiceImpl implements AuthService {

    @Resource
    private PwCacheApi<String> pwCacheApi;

    @Resource
    private SysUserService sysUserService;

    @Resource
    private SysRoleService sysRoleService;

    @Resource
    private SysResourceService sysResourceService;

    /**
     * 通过账号获取用户信息
     * @param account
     * @return
     */
    @Override
    public SysUser fetchSysUserByAccount(String account) {
        List<SysUser> users = sysUserService.fetchSysUserByAccount(account);
        return CollUtil.isEmpty(users) ? null : users.get(0);
    }

    /**
     * 校验用户状态是否正常
     * @param sysUser
     * @return
     */
    @Override
    public boolean validateSysUser(SysUser sysUser) {
        if (sysUser == null) {
            return false;
        }

        int flag = sysUser.getFlag();
        return flag == 1;
    }

    @Override
    public PwResponse login(AuthRequest request) {
        // 查询用户账号是否存在
        List<SysUser> sysUsers = sysUserService.fetchSysUserByAccount(request.getAccount());
        if(CollUtil.isEmpty(sysUsers)){
            return PwResponse.failure("用户账号或者密码错误");
        }

        // 查询用户密码是否正确
        SysUser sysUser = sysUsers.get(0);
        boolean bool = PasswordUtil.verify(sysUser.getPassword(), request.getPassword(), sysUser.getSalt());
        if(!bool){
            return PwResponse.failure("用户账号或者密码错误");
        }

        // 查询用户状态是否正确
        int flag = sysUser.getFlag();
        if(flag != 1){
            return PwResponse.failure("用户状态异常");
        }

        // 构建token
        Map<String, Object> claims = new HashMap<>();
        claims.put("account", request.getAccount());
        String token = JwtUtil.generate(PwApplicationContext.inst().getConfig().getKey(),
                PwApplicationContext.inst().getConfig().getSecret(), claims, 2*60*60);

        // token缓存
        PwLogin pwLogin = new PwLogin();
        pwLogin.setAccount(sysUser.getAccount());
        pwLogin.setToken(token);
        pwLogin.setId(sysUser.getId());
        pwCacheApi.set("LOGIN_"+token, JSON.toJSONString(pwLogin), 2*60*60*1000+2*60*1000);

        // 返回内容
        return PwResponse.success(MapBuilder.create()
                .put("account", request.getAccount())
                .put("token", token)
                .put("id", Convert.toStr(sysUser.getId())).build());
    }

    @Override
    public PwResponse logout(AuthRequest request) {
        return PwResponse.success();
    }

    /**
     * 通过用户id获取用户资源
     * @param userId
     * @return
     */
    private List<SysResource> fetchSysResourceByUserId(Long userId) {
        List<SysUser> sysUsers = sysUserService.fetchSysUserByUserId(userId);
        if(CollUtil.isEmpty(sysUsers)){
            return null;
        }

        // 获取用户角色
        List<SysRole> roles = sysRoleService.fetchRoleByUserId(userId);
        if(CollUtil.isEmpty(roles)){
            return null;
        }

        // 获取角色所有的资源
        List<SysResource> resources = sysResourceService.fetchResourceByRoleId(roles.stream().map(item -> Convert.toStr(item.getId(), "")).collect(Collectors.toList()));
        if(CollUtil.isEmpty(resources)){
            return null;
        }

        return resources;
    }

    /**
     * 通过登录信息获取资源信息
     * @return
     */
    private List<SysResource> fetchSysResourceByLoginContext() {
        PwLogin login = PwLoginContext.<PwLogin>inst().context();
        if(login == null){
            return null;
        }

        return fetchSysResourceByUserId(login.getId());
    }

    /**
     * 是否存在子资源
     * @param parent
     * @param resources
     * @return
     */
    private boolean hasChildren(String parent, List<SysResource> resources) {
        return resources.stream().anyMatch(item -> StrUtil.equals(Convert.toStr(item.getParentId(), "").trim(), parent));
    }

    /**
     * 通过父级id获取资源
     * @param parent
     * @param resources
     * @return
     */
    private List<Map<String, Object>> getByParent(Long parent, List<SysResource> resources) {
        return resources.stream().filter(item -> CharSequenceUtil.equals(Convert.toStr(item.getParentId(), "").trim(), Convert.toStr(parent, "").trim()))
                .map(item -> {
                    Map<String, Object> res = MapBuilder.<String, Object>create()
                            .put("key", Convert.toStr(item.getId(), "").trim())
                            .put("id", Convert.toStr(item.getResUrl(), "").trim())
                            .put("icon", Convert.toStr(item.getIcon(), "").trim())
                            .put("title", Convert.toStr(item.getResName(), "").trim())
                            .put("code", Convert.toStr(item.getResCode(), "").trim())
                            .build();
                    if(hasChildren(Convert.toStr(item.getId(), "").trim(), resources)){
                        res.put("children", getByParent(item.getId(), resources));
                    }
                    return res;
                }).collect(Collectors.toList());
    }

    /**
     * 按钮权限
     * @return
     */
    @Override
    public PwResponse permission() {
        List<SysResource> resources = fetchSysResourceByLoginContext();
        if(CollUtil.isEmpty(resources)){
            return PwResponse.success();
        }

        // 过滤按钮类型的资源
        List<String> buttons = resources.stream()
                                .filter(item -> item.getResType() == 3)
                                .map(SysResource::getResCode)
                                .toList();

        // 过滤页面类型的资源
        List<String> pages = resources.stream()
                .filter(item -> item.getResType() == 2)
                .map(SysResource::getResCode)
                .toList();


        return PwResponse.success(MapBuilder.create()
                                    .put("button", buttons)
                                    .put("page", pages)
                                    .build());
    }

    /**
     * 菜单权限
     * @return
     */
    @Override
    public PwResponse menu() {
        List<SysResource> resources = fetchSysResourceByLoginContext();
        if(CollUtil.isEmpty(resources)){
            return PwResponse.success();
        }


        // 过滤菜单类型的资源
        List<String> menus = resources.stream()
                .filter(item -> item.getResType() == 1)
                .map(SysResource::getResCode)
                .toList();

        if(CollUtil.isEmpty(menus)){
            return PwResponse.success(Collections.emptyList());
        }

        List<Map<String, Object>> res = getByParent(-1L, resources);
        if(CollUtil.isEmpty(res)){
            return PwResponse.success(Collections.emptyList());
        }

        return PwResponse.success(res);
    }

}
