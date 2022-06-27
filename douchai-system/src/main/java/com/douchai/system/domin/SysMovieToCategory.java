package com.douchai.system.domin;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

/**
 * 存储电影与电影类别的多对多联系
 * @Author: 华雨欣
 * @Create: 2020-11-30 22:07
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class SysMovieToCategory implements Serializable {

    private static final Long serialVersionUID = 1L;

    private Long movieId;

    private Long movieCategoryId;
}
