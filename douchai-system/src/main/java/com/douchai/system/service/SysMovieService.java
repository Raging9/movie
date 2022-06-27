package com.douchai.system.service;

import com.douchai.system.domin.SysMovie;
import com.douchai.system.domin.vo.SysMovieVo;

import java.util.List;


public interface SysMovieService {

    List<SysMovie> findAll(SysMovieVo sysMovieVo);

    SysMovie findById(Long id);

    SysMovie findOne(Long id);

    int add(SysMovie sysMovie);

    int update(SysMovie sysMovie);

    int delete(Long[] ids);

    SysMovie findMovieById(Long id);

    //获取单个影院上映的所有电影信息
    List<SysMovie> findByCinemaId(Long id);


    //获取各种榜单
    List<SysMovie> hotMovieList();

    List<SysMovie> domesticBoxOfficeList();

    List<SysMovie> europeanAndAmericanBoxOfficeList();

    List<SysMovie> top100List();
}
