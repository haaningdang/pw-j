package com.pw.modular.sys.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.convert.Convert;
import cn.hutool.core.map.MapBuilder;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pw.api.sys.entity.SysResource;
import com.pw.api.sys.pojo.dto.resource.ResourcePageTree;
import com.pw.api.sys.pojo.dto.resource.ResourceSelectTree;
import com.pw.api.sys.pojo.dto.resource.ResourceTree;
import com.pw.api.sys.pojo.request.resource.ResourceRequest;
import com.pw.api.sys.service.SysResourceService;
import com.pw.core.basic.response.PwResponse;
import com.pw.login.context.PwLoginContext;
import com.pw.login.pojo.PwLogin;
import com.pw.modular.sys.mapper.SysResourceMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SysResourceServiceImpl extends ServiceImpl<SysResourceMapper, SysResource> implements SysResourceService {

    @Override
    public List<SysResource> fetchResourceByRoleId(List<String> roles) {
        return this.baseMapper.fetchResourceByRoleId(roles);
    }

    /**
     * 获取所有资源
     * @return
     */
    @Override
    public List<SysResource> fetchResource() {
        return this.baseMapper.fetchResource();
    }

    /**
     * 获取有效资源
     * @return
     */
    private List<SysResource> fetchActiveResource(){
        return fetchResource().stream().filter(item -> item.getFlag() == 1).collect(Collectors.toList());
    }

    /**
     * 判断某一个父级是否存在子级资源
     * @param resources
     * @param parentId
     * @return
     */
    private boolean hasChild(List<SysResource> resources, Long parentId) {
        return resources.stream().anyMatch(resource -> resource.getParentId().equals(parentId));
    }

    /**
     * 罗列所有资源
     * @param resources
     * @param parentId
     * @return
     */
    private List<ResourceTree> createResourceTree(Long roleId, List<SysResource> resources, Long parentId) {
        // 第一级数据
        return resources.stream().filter(resource -> resource.getParentId().equals(parentId)).map(item -> {
            ResourceTree res = new ResourceTree();
            res.setId(item.getId());
            res.setChecked(roleId != null && roleHasResource(item.getId(), roleId));
            res.setSpread(false);
            res.setIcon(item.getIcon());
            res.setTitle(item.getResName());
            if(hasChild(resources, item.getId())){
                res.setChildren(createResourceTree(roleId, resources, item.getId()));
            }
            return res;
        }).collect(Collectors.toList());
    }

    // 判断角色是否存在某一个资源的权限
    private boolean roleHasResource(Long resourceId, Long roleId) {
        // 获取角色所有的资源
        return fetchResourceByRoleId(Collections.singletonList(Convert.toStr(roleId))).stream().anyMatch(resource -> resource.getId().equals(resourceId));
    }

    /**
     * 创建分页的资源树
     * @param resources
     * @param parentId
     * @return
     */
    private List<ResourcePageTree> createResourcePageTree(List<SysResource> resources, Long parentId) {
        // 第一级数据
        return resources.stream().filter(resource -> resource.getParentId().equals(parentId)).map(item -> {
            ResourcePageTree res = new ResourcePageTree();
            BeanUtils.copyProperties(item, res);
            if(hasChild(resources, item.getId())){
                res.setChildren(createResourcePageTree(resources, item.getId()));
            }
            return res;
        }).collect(Collectors.toList());
    }

    @Override
    public PwResponse page() {
        // 获取所有资源
        List<SysResource> resources = fetchResource();
        List<ResourcePageTree> res = createResourcePageTree(resources, -1L);

        return PwResponse.success(res);
    }

    @Override
    public PwResponse resource() {
        // 获取所有资源
        List<SysResource> resources = fetchResource();

        List<ResourceTree> res = createResourceTree(null, resources, -1L);

        return PwResponse.success(res);
    }

    /**
     * 获取角色的资源
     * @param request
     * @return
     */
    @Override
    public PwResponse fetchRoleResource(ResourceRequest request) {
        // 获取当前登录用户的角色
        PwLogin login = PwLoginContext.<PwLogin>inst().context();
        List<String> roles = new java.util.ArrayList<>(login.getRole().stream().map(Convert::toStr).toList());
        roles.add(Convert.toStr(request.getRoleId()));

        // 获取所有有效资源
        List<SysResource> resources = fetchResourceByRoleId(roles);

        List<SysResource> roleResources = fetchResourceByRoleId(Collections.singletonList(Convert.toStr(request.getRoleId())));
        List<Long> selected = roleResources.stream().map(SysResource::getId).toList();

        // 资源转树结构
        List<ResourceTree> res = createResourceTree(request.getRoleId(), resources, -1L);

        return PwResponse.success(MapBuilder.create().put("selected", selected).put("resource", res).build());
    }

    /**
     * 资源选择树
     * @param resources
     * @param parentId
     * @return
     */
    private List<ResourceSelectTree> createResourceSelectTree(List<SysResource> resources, Long parentId) {
        // 第一级数据
        return resources.stream().filter(resource -> resource.getParentId().equals(parentId)).map(item -> {
            ResourceSelectTree res = new ResourceSelectTree();
            res.setId(item.getId());
            res.setField(item.getResCode());
            res.setTitle(item.getResName());
            if(hasChild(resources, item.getId())){
                res.setChildren(createResourceSelectTree(resources, item.getId()));
            }
            return res;
        }).collect(Collectors.toList());
    }

    @Override
    public PwResponse select() {
        // 获取所有资源
        List<SysResource> resources = fetchResource();
        List<ResourceSelectTree> res = createResourceSelectTree(resources, -1L);

        ResourceSelectTree parent = new ResourceSelectTree();
        parent.setId(-1L);
        parent.setTitle("根节点");
        parent.setField("root");
        parent.setChildren(res);

        return PwResponse.success(parent);
    }

    /**
     * 根节点是否存在
     * @param parentId
     * @return
     */
    private boolean isParentExist(Long parentId) {
        if(parentId == -1L){
            return true;
        }
        List<SysResource> resources = this.baseMapper.fetchResourceById(parentId);
        return CollUtil.isNotEmpty(resources);
    }

    @Override
    public PwResponse add(ResourceRequest request) {
        // 根节点是否存在
        if(!isParentExist(request.getParent())){
            return PwResponse.failure("根节点不存在");
        }

        SysResource resource = new SysResource();
        BeanUtils.copyProperties(request, resource);
        resource.setParentId(request.getParent());

        this.save(resource);

        return PwResponse.success();
    }

    @Override
    public PwResponse update(ResourceRequest request) {
        List<SysResource> resources = this.baseMapper.fetchResourceById(request.getId());
        if(CollUtil.isEmpty(resources)){
            return PwResponse.failure("资源不存在");
        }

        SysResource resource = resources.get(0);
        BeanUtils.copyProperties(request, resource, "id", "parentId", "createTime");
        this.baseMapper.updateById(resource);

        return PwResponse.success();
    }

    /**
     * 获取子节点的id
     * @param resources
     * @param parents
     * @return
     */
    private List<Long> getChildren(List<SysResource> resources, List<Long> parents) {
        return resources.stream().filter(item -> parents.contains(item.getParentId())).map(SysResource::getId).collect(Collectors.toList());
    }

    /**
     * 判断是否有子节点
     * @param resources
     * @param parents
     * @return
     */
    private boolean hasChildren(List<SysResource> resources, List<Long> parents) {
        return resources.stream().filter(item -> parents.contains(item.getParentId())).map(SysResource::getId).findAny().isPresent();
    }

    @Override
    public PwResponse delete(ResourceRequest request) {
        // 获取所有的资源
        List<SysResource> resources = fetchResource();

        Long parent = request.getId();
        List<Long> parents = new java.util.ArrayList<>(Collections.singletonList(parent));
        List<Long> target = new java.util.ArrayList<>(Collections.singletonList(parent));
        // 筛选子资源
        while (hasChildren(resources, parents)) {
            List<Long> children = getChildren(resources, parents);
            parents.clear();
            parents.addAll(children);
            target.addAll(children);
        }

        // 删除资源
        for (Long id : target) {
            this.baseMapper.deleteById(id);
        }

        return PwResponse.success();
    }

}
