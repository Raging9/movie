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
public class SysCinemaBrand implements Serializable {

    private static final Long serialVersionUID = 1L;

    //影院区域id
    private Long cinemaBrandId;

    //影院区域名称
    @NotBlank(message = "影院品牌名称不能为空")
    private String cinemaBrandName;
}
