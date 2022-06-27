package com.douchai.system.mapper;

import com.douchai.system.domin.SysActorRole;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Author: 华雨欣
 * @Create: 2020-11-19 22:52
 */
@Mapper
public interface SysActorRoleMapper {

    /**
     * 查询所有
     * @return
     */
    List<SysActorRole> findAll();

    /**
     * 根据id查询
     * @param id
     * @return
     */
    SysActorRole findById(Long id);

    /**
     * 添加演员角色
     * @param sysActorRole
     * @return 影响行数
     */
    int add(SysActorRole sysActorRole);

    /**
     * 修改演员角色
     * @param sysActorRole
     */
    int update(SysActorRole sysActorRole);

    /**
     * 删除指定id的演员角色
     * @param id
     */
    int delete(Long id);

}
