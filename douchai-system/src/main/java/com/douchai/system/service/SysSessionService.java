package com.douchai.system.service;

import com.douchai.system.domin.SysSession;
import com.douchai.system.domin.vo.SysSessionVo;

import java.util.List;


public interface SysSessionService {

    List<SysSession> findByVo(SysSessionVo sysSessionVo);

    SysSession findById(Long id);

    SysSession findOne(Long id);

    int add(SysSession sysSession);

    int update(SysSession sysSession);

    int delete(Long[] id);

    List<SysSession> findByCinemaAndMovie(Long cinemaId, Long movieId);

}
