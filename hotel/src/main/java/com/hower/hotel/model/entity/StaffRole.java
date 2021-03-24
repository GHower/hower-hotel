package com.hower.hotel.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.hower.hotel.framework.model.BaseModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 'hower_hotel.staffdto' is not BASE TABLE
 * </p>
 *
 * @author Ghower
 * @since 2021-03-21
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("sys_staff_role")
public class StaffRole extends BaseModel {

    private static final long serialVersionUID = 1L;

    /**
     * 员工ID
     */
    private Integer uid;

    /**
     * 角色ID
     */
    private Integer roleId;


    public static final String UID = "uid";

    public static final String ROLE_ID = "role_id";

}
