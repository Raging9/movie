package com.douchai.system.mapper;

import com.douchai.system.domin.SysActorMovie;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


@Mapper
public interface SysActorMovieMapper {
    List<SysActorMovie> findAll(SysActorMovie sysActorMovie);

    int add(SysActorMovie sysActorMovie);

    int delete(SysActorMovie sysActorMovie);
}
