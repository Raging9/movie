package com.douchai.system.domin;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;



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
