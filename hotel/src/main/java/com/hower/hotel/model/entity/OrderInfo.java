package com.hower.hotel.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.hower.hotel.framework.model.BaseModel;
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
    private Integer rtId;

    /**
     * 客户ID
     */
    private Integer cId;

    /**
     * 订单操作员ID
     */
    private Integer sId;

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
     * 订单创建时间
     */
    private LocalDateTime createTime;

    /**
     * 订单修改时间
     */
    private LocalDateTime updateTime;


    public static final String RT_ID = "rt_id";

    public static final String C_ID = "c_id";

    public static final String S_ID = "s_id";

    public static final String IN_TIME = "in_time";

    public static final String OUT_TIME = "out_time";

    public static final String STATUS = "status";

    public static final String SOURCE = "source";

    public static final String REMARCKS = "remarcks";

    public static final String CREATE_TIME = "create_time";

    public static final String UPDATE_TIME = "update_time";

}
