package com.douchai.system.service;

import com.douchai.system.domin.SysCinema;
import com.douchai.system.domin.vo.SysCinemaVo;

import java.util.List;


public interface SysCinemaService {

    List<SysCinema> findAll(SysCinemaVo sysCinemaVo);

    SysCinema findById(Long id);

    int add(SysCinema sysCinema);

    int update(SysCinema sysCinema);

    int delete(Long[] ids);

    SysCinema findCinemaById(Long id);

}
