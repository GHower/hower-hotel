package com.hower.hotel.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.hower.hotel.framework.model.BaseModel;
import java.time.LocalDateTime;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 'hower_hotel.orderdto' is not BASE TABLE
 * </p>
 *
 * @author Ghower
 * @since 2021-03-21
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("room_info")
public class RoomInfo extends BaseModel {

    private static final long serialVersionUID = 1L;

    /**
     * 房间类型，->房间类型表.id
     */
    private Integer typeId;

    /**
     * 房间状态
     */
    private Integer status;

    /**
     * 房间号码
     */
    private Integer number;

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

    /**
     * 修改时间
     */
    private LocalDateTime updateTime;


    public static final String TYPE_ID = "type_id";

    public static final String STATUS = "status";

    public static final String NUMBER = "number";

    public static final String CREATE_UID = "create_uid";

    public static final String UPDATE_UID = "update_uid";

    public static final String CREATE_TIME = "create_time";

    public static final String UPDATE_TIME = "update_time";

}
