package com.douchai.system.mapper;

import com.douchai.system.domin.SysMovieRuntime;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


@Mapper
public interface SysMovieRuntimeMapper {

    /**
     * 查询所有
     * @return
     */
    List<SysMovieRuntime> findAll();

    /**
     * 根据id查询
     * @param id
     * @return
     */
    SysMovieRuntime findById(Long id);

    /**
     * 添加电影播放时段
     * @param sysMovieRuntime
     * @return 影响行数
     */
    int add(SysMovieRuntime sysMovieRuntime);

    /**
     * 修改电影播放时段
     * @param sysMovieRuntime
     */
    int update(SysMovieRuntime sysMovieRuntime);

    /**
     * 删除指定id的电影播放时段
     * @param id
     */
    int delete(Long id);


}
