package com.douchai.system.service.impl;

import com.douchai.system.domin.SysCinemaArea;
import com.douchai.system.mapper.SysCinemaAreaMapper;
import com.douchai.system.service.SysCinemaAreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class SysCinemaAreaServiceImpl implements SysCinemaAreaService {

    @Autowired
    private SysCinemaAreaMapper sysCinemaAreaMapper;

    @Override
    public List<SysCinemaArea> findAll() {
        return sysCinemaAreaMapper.findAll();
    }

    @Override
    public SysCinemaArea findById(Long id) {
        return sysCinemaAreaMapper.findById(id);
    }

    @Override
    public int add(SysCinemaArea sysCinemaArea) {
        return sysCinemaAreaMapper.add(sysCinemaArea);
    }

    @Override
    public int update(SysCinemaArea sysCinemaArea) {
        return sysCinemaAreaMapper.update(sysCinemaArea);
    }

    @Override
    public int delete(Long[] ids) {
        int rows = 0;
        for(Long id : ids){
            rows += sysCinemaAreaMapper.delete(id);
        }
        return rows;
    }
}
