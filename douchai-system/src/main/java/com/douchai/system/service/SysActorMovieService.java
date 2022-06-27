package com.douchai.system.service;

import com.douchai.system.domin.SysActorMovie;

import java.util.List;



public interface SysActorMovieService {
    List<SysActorMovie> findAll(SysActorMovie sysActorMovie);

    int add(SysActorMovie sysActorMovie);

    int delete(SysActorMovie sysActorMovie);
}
