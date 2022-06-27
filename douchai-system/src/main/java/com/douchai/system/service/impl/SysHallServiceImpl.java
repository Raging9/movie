package com.douchai.system.service.impl;

import com.douchai.system.domin.SysHall;
import com.douchai.system.mapper.SysHallMapper;
import com.douchai.system.service.SysHallService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;



@Service
public class SysHallServiceImpl implements SysHallService {

    @Autowired
    private SysHallMapper sysHallMapper;

    @Override
    public List<SysHall> findAll(SysHall sysHall) {
        return sysHallMapper.findAll(sysHall);
    }

    @Override
    public SysHall findByCinemaIdAndHallId(SysHall sysHall) {
        return sysHallMapper.findByCinemaIdAndHallId(sysHall);
    }

    @Override
    public int add(SysHall sysHall) {
        return sysHallMapper.add(sysHall);
    }

    @Override
    public int update(SysHall sysHall) {
        return sysHallMapper.update(sysHall);
    }

    @Override
    public int delete(SysHall[] sysHalls) {
        int rows = 0;
        for (SysHall sysHall : sysHalls) {
            rows += sysHallMapper.delete(sysHall);
        }
        return rows;
    }
}
