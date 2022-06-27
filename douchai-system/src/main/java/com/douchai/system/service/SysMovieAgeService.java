package com.douchai.system.service;

import com.douchai.system.domin.SysMovieAge;

import java.util.List;

/**
 * @Author: 华雨欣
 * @Create: 2020-11-14 22:57
 */
public interface SysMovieAgeService {

    List<SysMovieAge> findAll();

    SysMovieAge findById(Long id);

    int add(SysMovieAge sysMovieAge);

    int update(SysMovieAge sysMovieAge);

    int delete(Long[] ids);

}
