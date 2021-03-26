package com.hower.hotel.model.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.hower.hotel.framework.model.BaseModel;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 *
 * </p>
 *
 * @author Ghower
 * @since 2021-03-21
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("order_info")
public class OrderInfo extends BaseModel {

    private static final long serialVersionUID = 1L;

    /**
     * 房间ID
     */
    private Integer rId;

    /**
     * 客户ID
     */
    private String cId;

//    /**
//     * 订单操作员ID
//     */
//    private Integer sId;
    /**
     * 订单下的住客信息
     */
    private String guestId;
    /**
     * 入住时间
     */
    private LocalDateTime inTime;

    /**
     * 离店时间
     */
    private LocalDateTime outTime;

    /**
     * 订单状态
     */
    private String status;

    /**
     * 订单来源
     */
    private String source;

    /**
     * 订单备注
     */
    private String remarcks;
    /**
     * 实付款金额
     */
    private BigDecimal payFact;
    /**
     * 订单创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    /**
     * 订单修改时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;


    public static final String R_ID = "r_id";

    public static final String C_ID = "c_id";

//    public static final String S_ID = "s_id";
    public static final String GUEST_ID = "guest_id";

    public static final String IN_TIME = "in_time";

    public static final String OUT_TIME = "out_time";

    public static final String STATUS = "status";

    public static final String SOURCE = "source";

    public static final String PAY_FACT = "pay_fact";

    public static final String REMARCKS = "remarcks";

    public static final String CREATE_TIME = "create_time";

    public static final String UPDATE_TIME = "update_time";

}
