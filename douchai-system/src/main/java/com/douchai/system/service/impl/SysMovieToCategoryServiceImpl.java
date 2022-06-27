package com.douchai.system.service.impl;

import com.douchai.system.domin.SysMovieToCategory;
import com.douchai.system.mapper.SysMovieToCategoryMapper;
import com.douchai.system.service.SysMovieToCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class SysMovieToCategoryServiceImpl implements SysMovieToCategoryService {

    @Autowired
    private SysMovieToCategoryMapper sysMovieToCategoryMapper;

    @Override
    public List<SysMovieToCategory> findAll(SysMovieToCategory sysMovieToCategory) {
        return sysMovieToCategoryMapper.findAll(sysMovieToCategory);
    }

    @Override
    public int add(SysMovieToCategory sysMovieToCategory) {
        return sysMovieToCategoryMapper.add(sysMovieToCategory);
    }

    @Override
    public int delete(SysMovieToCategory sysMovieToCategory) {
        return sysMovieToCategoryMapper.delete(sysMovieToCategory);
    }
}
