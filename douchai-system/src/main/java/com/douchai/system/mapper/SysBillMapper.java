package com.douchai.system.mapper;

import com.douchai.system.domin.SysBill;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


@Mapper
public interface SysBillMapper {

    List<SysBill> findAll(SysBill sysBill);

    SysBill findById(Long id);

    int add(SysBill sysBill);

    int update(SysBill sysBill);

    int delete(Long id);

    Double todayBoxOffice();
    
}
