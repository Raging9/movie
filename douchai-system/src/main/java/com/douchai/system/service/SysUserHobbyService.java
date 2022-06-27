package com.douchai.system.service;

import com.douchai.system.domin.SysUserHobby;

import java.util.List;


public interface SysUserHobbyService {

    List<SysUserHobby> findAll();

    SysUserHobby findById(Long id);

    int add(SysUserHobby sysUserHobby);

    int update(SysUserHobby sysUserHobby);

    int delete(Long[] ids);

}
