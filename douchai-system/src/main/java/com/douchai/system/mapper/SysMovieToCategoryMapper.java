package com.douchai.system.mapper;

import com.douchai.system.domin.SysMovieToCategory;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Author: 华雨欣
 * @Create: 2020-11-30 22:13
 */
@Mapper
public interface SysMovieToCategoryMapper {

    List<SysMovieToCategory> findAll(SysMovieToCategory sysMovieToCategory);

    int add(SysMovieToCategory sysMovieToCategory);

    int delete(SysMovieToCategory sysMovieToCategory);

}
