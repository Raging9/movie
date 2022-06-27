package com.douchai.system.mapper;

import com.douchai.system.domin.SysHall;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author lxd
 * @create 2020-11-25 10:16
 */
@Mapper
public interface SysHallMapper {
    List<SysHall> findAll(SysHall sysHall);

    SysHall findByCinemaIdAndHallId(SysHall sysHall);

    int add(SysHall sysHall);

    int update(SysHall sysHall);

    int delete(SysHall sysHall);
}
