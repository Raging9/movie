package com.douchai.system.domin.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

/**
 * @Author: 华雨欣
 * @Create: 2020-12-01 16:32
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class SysCinemaVo implements Serializable {

    private String cinemaName;

    private Long cinemaBrandId;

    private String cinemaAddress;

    private Long cinemaAreaId;

    private Boolean isTicketChanged;

    private Boolean isRefunded;

    private Long hallCategoryId;

}
