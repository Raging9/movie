package com.douchai.system.service.impl;

import com.douchai.system.domin.SysBill;
import com.douchai.system.mapper.SysBillMapper;
import com.douchai.system.service.SysBillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: 华雨欣
 * @Create: 2020-11-30 14:25
 */
@Service
public class SysBillServiceImpl implements SysBillService {

    @Autowired
    private SysBillMapper sysBillMapper;

    @Override
    public List<SysBill> findAll(SysBill sysBill) {
        return sysBillMapper.findAll(sysBill);
    }

    @Override
    public SysBill findById(Long id) {
        return sysBillMapper.findById(id);
    }

    @Override
    public Object add(SysBill sysBill) {
        int rows = sysBillMapper.add(sysBill);
        return rows > 0 ? sysBill : rows;
    }

    @Override
    public int update(SysBill sysBill) {
        return sysBillMapper.update(sysBill);
    }

    @Override
    public int delete(Long[] ids) {
        int rows = 0;
        for(Long id : ids){
            rows += sysBillMapper.delete(id);
        }
        return rows;
    }

    @Override
    public Double todayBoxOffice() {
        return sysBillMapper.todayBoxOffice();
    }
}
