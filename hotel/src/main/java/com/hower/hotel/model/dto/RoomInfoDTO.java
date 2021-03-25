package com.hower.hotel.model.dto;

import com.hower.hotel.framework.model.BaseModel;
import com.hower.hotel.model.entity.RoomInfo;
import com.hower.hotel.model.entity.RoomType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoomInfoDTO extends BaseModel {
    /**
     * 房间类型名称，如豪华大床房
     */
    private String name;

    /**
     * 该类型房间的基础定价
     */
    private BigDecimal price;

    /**
     * 创建者ID
     */
    private Integer createUid;

    /**
     * 修改者ID
     */
    private Integer updateUid;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    private List<RoomInfo> roomInfo;

    /**
     * 修改时间
     */
    private LocalDateTime updateTime;
    public static final String NAME = "name";

    public static final String PRICE = "price";

    public static final String CREATE_UID = "create_uid";

    public static final String UPDATE_UID = "update_uid";

    public static final String CREATE_TIME = "create_time";

    public static final String UPDATE_TIME = "update_time";
}
