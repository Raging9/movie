package com.douchai.system.domin;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class SysActor implements Serializable {

    private static final Long serialVersionUID = 1L;

    //演员id
    private Long actorId;

    //演员名
    @NotBlank(message = "演员名不能为空")
    private String actorName;

    //演员图片
//    @NotBlank(message = "演员图片不能为空")
    private String actorPhoto;

    //演员身高
    @Range(min = 100, max = 300, message = "身高需要在1m到3m之间")
    private Double actorHeight;

    @Min(value = 0, message = "年龄不得低于0")
    @Max(value = 100, message = "年龄不得高于100")
    //演员年龄
    private Integer actorAge;

    @NotNull(message = "性别不能为空")
    //演员性别
    private Boolean actorGender;

//    @NotBlank(message = "毕业院校不得为空")
    //演员毕业院校
    private String actorSchool;

    //演员民族
    private String actorNation;

    //演员血型
    private String actorBloodType;

    //演员星座
    private String actorConstellation;

    //演员国籍
    private String actorNationality;

    //演员详细信息
    private String actorInformation;

    private List<SysMovie> movieList;

    private List<SysActorRole> actorRoleList;

}
