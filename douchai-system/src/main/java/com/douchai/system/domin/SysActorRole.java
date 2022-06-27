package com.douchai.system.domin;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.List;

/**
 * 演员角色实体类
 * @Author: 华雨欣
 * @Create: 2020-11-19 22:41
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class SysActorRole implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long actorRoleId;

    @NotBlank(message = "演员角色名称不能为空")
    private String actorRoleName;

    //多表连接
    private List<SysActor> actorList;
}
