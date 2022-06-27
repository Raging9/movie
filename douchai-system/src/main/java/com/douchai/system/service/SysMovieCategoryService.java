package com.douchai.system.service;

import com.douchai.system.domin.SysMovieCategory;

import java.util.List;

/**
 * @author lxd
 * @create 2020-11-19 18:40
 */
public interface SysMovieCategoryService {
    List<SysMovieCategory> findAll();

    SysMovieCategory findById(Long id);

    int add(SysMovieCategory sysMovieCategory);

    int update(SysMovieCategory sysMovieCategory);

    int delete(Long[] ids);
}
