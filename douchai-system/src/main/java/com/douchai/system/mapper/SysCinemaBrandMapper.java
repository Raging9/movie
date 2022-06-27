package com.douchai.system.mapper;

import com.douchai.system.domin.SysCinemaBrand;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SysCinemaBrandMapper {
    
    List<SysCinemaBrand> findAll();
    
    SysCinemaBrand findById(Long id);
    
    int add(SysCinemaBrand sysCinemaBrand);
    
    int update(SysCinemaBrand sysCinemaBrand);

    int delete(Long id);
}
