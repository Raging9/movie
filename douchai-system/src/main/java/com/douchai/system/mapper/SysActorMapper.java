package com.douchai.system.mapper;

import com.douchai.system.domin.SysActor;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author lxd
 * @create 2020-11-23 15:34
 */
@Mapper
public interface SysActorMapper {
    List<SysActor> findAll(SysActor sysActor);

    SysActor findById(Long id);

    int add(SysActor sysActor);

    int update(SysActor sysActor);

    int delete(Long id);

    SysActor findActorById(Long id);

    /**
     * 根据电影id查询所有主演名称
     * @param id
     * @return
     */
    List<String> findaMajorActorNames(Long id);
}
