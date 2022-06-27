package com.douchai.system.service;

import com.douchai.system.domin.SysResource;

import java.util.List;

/**
 * @Author: 华雨欣
 * @Create: 2020-11-19 22:53
 */
public interface SysResourceService {
    List<SysResource> findAll();

    List<SysResource> findWithChildren();

    List<SysResource> findAllWithAllChildren();

    SysResource findById(Long id);

    int add(SysResource sysResource);

    int update(SysResource sysResource);

    int delete(Long[] ids);
}
