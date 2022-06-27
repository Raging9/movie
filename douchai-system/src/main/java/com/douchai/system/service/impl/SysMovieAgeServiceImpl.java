package com.douchai.system.service.impl;

import com.douchai.system.domin.SysMovieAge;
import com.douchai.system.mapper.SysMovieAgeMapper;
import com.douchai.system.service.SysMovieAgeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: 华雨欣
 * @Create: 2020-11-14 22:59
 */
@Service
public class SysMovieAgeServiceImpl implements SysMovieAgeService {

    @Autowired
    SysMovieAgeMapper sysMovieAgeMapper;

    @Override
    public List<SysMovieAge> findAll() {
        return sysMovieAgeMapper.findAll();
    }

    @Override
    public SysMovieAge findById(Long id) {
        return sysMovieAgeMapper.findById(id);
    }

    @Override
    public int add(SysMovieAge sysMovieAge) {
        return sysMovieAgeMapper.add(sysMovieAge);
    }

    @Override
    public int update(SysMovieAge sysMovieAge) {
        return sysMovieAgeMapper.update(sysMovieAge);
    }

    @Override
    public int delete(Long[] ids) {
        int rows = 0;
        for(Long id : ids)
            rows += sysMovieAgeMapper.delete(id);
        return rows;
    }
}
