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
@TableName("sys_permission")
public class SysPermission extends BaseModel {

    private static final long serialVersionUID = 1L;

    /**
     * 权限名称
     */
    private String permissionName;

    /**
     * 权限
     */
    private String permission;


    public static final String PERMISSION_NAME = "permission_name";

    public static final String PERMISSION = "permission";

}
