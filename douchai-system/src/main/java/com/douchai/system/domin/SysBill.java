package com.douchai.system.domin;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

/**
 * 订单实体类
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class SysBill implements Serializable {

    private static final Long serialVersionUID = 1L;

    private Long billId;

    //订单状态，0表示未支付，1表示已完成
    private Boolean billState;

    //下订单的用户id
    @NotNull(message = "下订单用户不能为空")
    private Long userId;

    //订单所属的场次
    @NotNull(message = "订单所属场次不能为空")
    private Long sessionId;

    //订单的座位，如：1排10号、A排5号
    @NotBlank(message = "订单所选座位不能为空")
    private String seats;

    private Date billDate;


    //多表连接
    private SysSession sysSession;

    private SysUser sysUser;

}
