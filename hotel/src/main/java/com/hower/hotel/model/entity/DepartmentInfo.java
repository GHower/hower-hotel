package com.hower.hotel.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
@TableName("department_info")
public class DepartmentInfo extends BaseModel {

    private static final long serialVersionUID = 1L;

    /**
     * 父级部门
     */
    private Integer pId;

    /**
     * 部门名称
     */
    private String name;

    /**
     * 部门状态
     */
    private Integer status;

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


    public static final String P_ID = "p_id";

    public static final String NAME = "name";

    public static final String STATUS = "status";

    public static final String CREATE_UID = "create_uid";

    public static final String UPDATE_UID = "update_uid";

    public static final String CREATE_TIME = "create_time";

    public static final String UPDATE_TIME = "update_time";

}
