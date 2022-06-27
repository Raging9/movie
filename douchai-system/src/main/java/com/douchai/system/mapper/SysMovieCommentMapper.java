package com.douchai.system.mapper;

import com.douchai.system.domin.SysMovieComment;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


@Mapper
public interface SysMovieCommentMapper {

    /**
     * 查询所有
     * @return
     */
    List<SysMovieComment> findAll();

    /**
     * 查询一个
     * @param sysMovieComment 包含电影、用户、时间的实体类
     * @return
     */
    SysMovieComment findOne(SysMovieComment sysMovieComment);

    /**
     * 根据电影id查询
     * @param movieId
     * @return
     */
    List<SysMovieComment> findByMovieId(Long movieId);

    /**
     * 根据用户id查询
     * @param userId
     * @return
     */
    List<SysMovieComment> findByUserId(Long userId);

    /**
     * 添加
     * @param sysMovieComment
     * @return 影响行数
     */
    int add(SysMovieComment sysMovieComment);

    /**
     * 修改
     * @param sysMovieComment
     */
    int update(SysMovieComment sysMovieComment);

    /**
     * 删除，只需要movieId,userId,commentTime
     * @param sysMovieComment
     * @return
     */
    int delete(SysMovieComment sysMovieComment);

}
