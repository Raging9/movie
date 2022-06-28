package com.douchai.system.domin;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;


@ToString
@NoArgsConstructor
@AllArgsConstructor
@Data
public class SysCinemaArea implements Serializable {

    private static final Long serialVersionUID = 1L;

    //影院区域id
    private Long cinemaAreaId;

    //影院区域名称
    @NotBlank(message = "影院区域名称不能为空")
    private String cinemaAreaName;

    //影院所属省份
    @NotBlank(message = "影院所属省份不能为空")
    private String province;

    //影院所属城市
    @NotBlank(message = "影院所属城市不能为空")
    private String city;

}
