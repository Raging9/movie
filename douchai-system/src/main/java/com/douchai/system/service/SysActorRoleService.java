package com.douchai.system.service;

import com.douchai.system.domin.SysActorRole;

import java.util.List;

/**
 * @Author: 华雨欣
 * @Create: 2020-11-19 22:53
 */
public interface SysActorRoleService {
    List<SysActorRole> findAll();

    SysActorRole findById(Long id);

    int add(SysActorRole sysActorRole);

    int update(SysActorRole sysActorRole);

    int delete(Long[] ids);
}
