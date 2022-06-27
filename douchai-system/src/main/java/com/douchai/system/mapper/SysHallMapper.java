package com.douchai.system.mapper;

import com.douchai.system.domin.SysHall;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


@Mapper
public interface SysHallMapper {
    List<SysHall> findAll(SysHall sysHall);

    SysHall findByCinemaIdAndHallId(SysHall sysHall);

    int add(SysHall sysHall);

    int update(SysHall sysHall);

    int delete(SysHall sysHall);
}
