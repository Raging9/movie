package com.douchai.system.domin;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author lxd
 * @create 2020-11-27 10:09
 */

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class SysActorMovie implements Serializable {
    private static final Long SerialVersionUID = 1L;

    private Long movieId;

    private Long actorId;

    private Long actorRoleId;
}
