package com.douchai.system.mapper;

import com.douchai.system.domin.SysCinema;
import com.douchai.system.domin.vo.SysCinemaVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


@Mapper
public interface SysCinemaMapper {

    List<SysCinema> findAll(SysCinemaVo sysCinemaVo);

    SysCinema findById(Long id);

    int add(SysCinema sysCinema);

    int update(SysCinema sysCinema);

    int delete(Long id);

    //前台展示单个影院信息，返回包含影院、上映中的所有电影信息
    SysCinema findCinemaById(Long id);

}
