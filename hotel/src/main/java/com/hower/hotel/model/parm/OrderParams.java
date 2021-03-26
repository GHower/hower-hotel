package com.hower.hotel.model.parm;

import com.baomidou.mybatisplus.annotation.TableName;
import com.hower.hotel.framework.model.BaseModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.math.BigDecimal;

@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class OrderParams extends BaseModel {
    /**
     * 房间ID
     */
    private Integer rId;

    /**
     * 客户ID
     */
    private String cId;

    /**
     * 订单操作员ID
     */
    private Integer sId;
    /**
     * 实付款金额
     */
    private BigDecimal payFact;
    /**
     * 订单备注
     */
    private String remarcks;
    /**
     * 订单来源
     */
    private String source;
}
