package com.douchai.system.domin;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

/**
 * 当前登录用户
 * @Author: 华雨欣
 * @Create: 2020-12-07 22:56
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class LoginUser implements Serializable {
    //登录的用户信息
    private SysUser sysUser;
    //用户管理的影院id
    private Long cinemaId;
    //用户管理的影院名称
    private String cinemaName;

    //系统颁发的token
    private String token;

}
