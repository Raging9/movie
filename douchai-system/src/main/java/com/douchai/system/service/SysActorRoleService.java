package com.douchai.system.service;

import com.douchai.system.domin.SysActorRole;

import java.util.List;


public interface SysActorRoleService {
    List<SysActorRole> findAll();

    SysActorRole findById(Long id);

    int add(SysActorRole sysActorRole);

    int update(SysActorRole sysActorRole);

    int delete(Long[] ids);
}
