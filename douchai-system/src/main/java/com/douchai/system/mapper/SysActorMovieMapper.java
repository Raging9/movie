package com.douchai.system.mapper;

import com.douchai.system.domin.SysActorMovie;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author lxd
 * @create 2020-11-27 10:13
 */
@Mapper
public interface SysActorMovieMapper {
    List<SysActorMovie> findAll(SysActorMovie sysActorMovie);

    int add(SysActorMovie sysActorMovie);

    int delete(SysActorMovie sysActorMovie);
}
