package com.hower.hotel.model.dto;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.hower.hotel.framework.model.BaseModel;
import com.hower.hotel.model.entity.StaffInfo;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * <p>
 * 
 * </p>
 *
 * @author Ghower
 * @since 2021-03-26
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class OrderPayInfoDTO extends BaseModel {

    private static final long serialVersionUID = 1L;

    /**
     * 订单ID
     */
    private Integer oId;
    /**
     * 员工信息
     */
    private StaffInfo staffInfo;
    /**
     * 付款方式,[现金，微信，支付宝，刷某行卡]
     */
    private String payWay;

    private BigDecimal money;
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;
}
