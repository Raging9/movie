package com.douchai.system.service.impl;

import com.douchai.system.domin.SysActorMovie;
import com.douchai.system.mapper.SysActorMovieMapper;
import com.douchai.system.service.SysActorMovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class SysActorMovieServiceImpl implements SysActorMovieService {

    @Autowired
    private SysActorMovieMapper sysActorMovieMapper;

    @Override
    public List<SysActorMovie> findAll(SysActorMovie sysActorMovie) {
        return sysActorMovieMapper.findAll(sysActorMovie);
    }

    @Override
    public int add(SysActorMovie sysActorMovie) {
        return sysActorMovieMapper.add(sysActorMovie);
    }

    @Override
    public int delete(SysActorMovie sysActorMovie) {
        return sysActorMovieMapper.delete(sysActorMovie);
    }
}
