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
 * @since 2021-03-26
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("sys_role_permission")
public class SysRolePermission extends BaseModel {

    private static final long serialVersionUID = 1L;

    private Integer permissionId;

    private Integer roleId;


    public static final String PERMISSION_ID = "permission_id";

    public static final String ROLE_ID = "role_id";

}
