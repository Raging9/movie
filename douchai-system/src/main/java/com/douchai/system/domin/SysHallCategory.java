package com.douchai.system.domin;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;


@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class SysHallCategory implements Serializable{
    //序列号
    private static final long serialVersionUID = 1L;

    //影厅分类id
    private Long hallCategoryId;

    //影厅分类名称
    @NotBlank(message = "影厅分类名称不能为空")
    private String hallCategoryName;
}
