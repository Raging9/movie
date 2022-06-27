package com.douchai.system.service;

import com.douchai.system.domin.SysCinemaBrand;

import java.util.List;

public interface SysCinemaBrandService {
    List<SysCinemaBrand> findAll();

    SysCinemaBrand findById(Long id);

    int add(SysCinemaBrand sysCinemaBrand);

    int update(SysCinemaBrand sysCinemaBrand);

    int delete(Long[] ids);
}
