package com.hower.hotel.model.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.hower.hotel.framework.model.BaseModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

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
@TableName("order_pay_info")
public class OrderPayInfo extends BaseModel {

    private static final long serialVersionUID = 1L;

    /**
     * 订单ID
     */
    private Integer oId;
    /**
     * 员工ID
     */
    private Integer sId;
    /**
     * 付款方式,[现金，微信，支付宝，刷某行卡]
     */
    private String payWay;

    private BigDecimal money;
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    public static final String O_ID = "o_id";
    public static final String S_ID = "s_id";

    public static final String PAY_WAY = "pay_way";

    public static final String MONEY = "money";
    public static final String CREATE_Time = "create_time";

}
