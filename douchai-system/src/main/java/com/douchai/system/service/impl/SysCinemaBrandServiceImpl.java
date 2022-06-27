package com.douchai.system.service.impl;

import com.douchai.system.domin.SysCinemaBrand;
import com.douchai.system.mapper.SysCinemaBrandMapper;
import com.douchai.system.service.SysCinemaBrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class SysCinemaBrandServiceImpl implements SysCinemaBrandService {

    @Autowired
    private SysCinemaBrandMapper sysCinemaBrandMapper;

    @Override
    public List<SysCinemaBrand> findAll() {
        return sysCinemaBrandMapper.findAll();
    }

    @Override
    public SysCinemaBrand findById(Long id) {
        return sysCinemaBrandMapper.findById(id);
    }

    @Override
    public int add(SysCinemaBrand sysCinemaBrand) {
        return sysCinemaBrandMapper.add(sysCinemaBrand);
    }

    @Override
    public int update(SysCinemaBrand sysCinemaBrand) {
        return sysCinemaBrandMapper.update(sysCinemaBrand);
    }

    @Override
    public int delete(Long[] ids) {
        int rows = 0;
        for(Long id : ids){
            rows += sysCinemaBrandMapper.delete(id);
        }
        return rows;
    }
}
