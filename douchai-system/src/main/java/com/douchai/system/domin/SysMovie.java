package com.douchai.system.domin;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Date;
import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class SysMovie implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long movieId;

    //电影中文名
    @NotBlank(message = "电影中文名不能为空")
    private String movieNameCn;

    //电影英文名
    @NotBlank(message = "电影英文名不能为空")
    private String movieNameEn;

    //电影时长
    private Integer movieLength;

    //电影海报
    private String moviePoster;

    //电影区域id
    private Long movieAreaId;

    //电影年代id
    private Long movieAgeId;

    //上映日期
    private Date releaseDate;

    //电影评分
    private Double movieScore;

    //电影总票房
    private Double movieBoxOffice;

    //评分人数
    private Integer movieRateNum;

    //电影简介
    private String movieIntroduction;

    //电影图集
    private String moviePictures;


    //电影区域
    private SysMovieArea sysMovieArea;

    //电影年代
    private SysMovieAge sysMovieAge;


    //电影的参演人员角色，每个角色分别对应若干演员
    private List<SysActorRole> actorRoleList;

    //影评列表
    private List<SysMovieComment> movieCommentList;

    //电影的类别
    private List<SysMovieCategory> movieCategoryList;

    //电影主演名称，在影院中显示
    private List<String> majorActorNameList;

}
