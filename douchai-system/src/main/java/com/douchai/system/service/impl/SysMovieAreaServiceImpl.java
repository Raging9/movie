package com.douchai.system.service.impl;

import com.douchai.system.domin.SysMovieArea;
import com.douchai.system.mapper.SysMovieAreaMapper;
import com.douchai.system.service.SysMovieAreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class SysMovieAreaServiceImpl implements SysMovieAreaService {
    @Autowired
    SysMovieAreaMapper sysMovieAreaMapper;

    @Override
    public List<SysMovieArea> findAll() {
        return sysMovieAreaMapper.findAll();
    }

    @Override
    public SysMovieArea findById(Long id) {
        return sysMovieAreaMapper.findById(id);
    }

    @Override
    public int add(SysMovieArea sysMovieArea) {
        return sysMovieAreaMapper.add(sysMovieArea);
    }

    @Override
    public int update(SysMovieArea sysMovieArea) {
        return sysMovieAreaMapper.update(sysMovieArea);
    }

    @Override
    public int delete(Long[] ids) {
        int rows = 0;
        for (Long id : ids) {
            rows += sysMovieAreaMapper.delete(id);
        }
        return rows;
    }
}
