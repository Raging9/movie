package com.douchai.system.domin;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * 电影年代
 * @Author: 华雨欣
 * @Create: 2020-11-14 22:46
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class SysMovieAge implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long movieAgeId;//年代id

    @NotBlank(message = "年代名称不能为空")
    private String movieAgeName;//年代名称
}
