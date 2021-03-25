package com.hower.hotel.model.dto;

import com.hower.hotel.framework.model.BaseModel;
import com.hower.hotel.framework.model.convert.Convert;
import com.hower.hotel.model.entity.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class OrderInfoDTO extends Convert {
    private OrderInfo orderInfo;//订单基础信息
    private CustomerInfo customerInfo;//预订人信息
    private StaffInfo staffInfo;//操作员信息
    private List<CustomerInfo> guests;//住客信息
    private RoomInfo roomInfo;
    private String roomType;//房间类型
}
