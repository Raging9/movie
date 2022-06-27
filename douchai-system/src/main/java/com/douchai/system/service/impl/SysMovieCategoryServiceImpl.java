package com.douchai.system.service.impl;

import com.douchai.system.domin.SysMovieCategory;
import com.douchai.system.mapper.SysMovieCategoryMapper;
import com.douchai.system.service.SysMovieCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class SysMovieCategoryServiceImpl implements SysMovieCategoryService {

    @Autowired
    private SysMovieCategoryMapper sysMovieCategoryMapper;

    @Override
    public List<SysMovieCategory> findAll() {
        return sysMovieCategoryMapper.findAll();
    }

    @Override
    public SysMovieCategory findById(Long id) {
        return sysMovieCategoryMapper.findById(id);
    }

    @Override
    public int add(SysMovieCategory sysMovieCategory) {
        return sysMovieCategoryMapper.add(sysMovieCategory);
    }

    @Override
    public int update(SysMovieCategory sysMovieCategory) {
        return sysMovieCategoryMapper.update(sysMovieCategory);
    }

    @Override
    public int delete(Long[] ids) {
        int rows = 0;
        for (Long id : ids) {
            rows += sysMovieCategoryMapper.delete(id);
        }
        return rows;
    }
}
