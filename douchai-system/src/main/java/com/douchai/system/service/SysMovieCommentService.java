package com.douchai.system.service;

import com.douchai.system.domin.SysMovieComment;

import java.util.List;


public interface SysMovieCommentService {

    List<SysMovieComment> findAll();

    SysMovieComment findOne(SysMovieComment sysMovieComment);

    List<SysMovieComment> findByMovieId(Long movieId);

    List<SysMovieComment> findByUserId(Long userId);

    int add(SysMovieComment sysMovieComment);

    int update(SysMovieComment sysMovieComment);

    int delete(SysMovieComment[] pks);

}
