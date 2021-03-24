package com.hower.hotel.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.hower.hotel.framework.model.BaseModel;
import java.time.LocalDateTime;
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
@TableName("sys_menu")
public class Menu extends BaseModel {

    private static final long serialVersionUID = 1L;

    /**
     * 父级菜单，一级为0
     */
    private Integer parentId;

    /**
     * 菜单名称
     */
    private String title;

    /**
     * 菜单类型,0-目录，1-菜单，3-按钮

     */
    private String type;

    /**
     * 菜单级数
     */
    private Integer level;

    /**
     * 前端名称
     */
    private String name;

    /**
     * 前端图标
     */
    private String icon;

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

    /**
     * 状态 0：禁用 1：正常
     */
    private Integer status;


    public static final String PARENT_ID = "parent_id";

    public static final String TITLE = "title";

    public static final String TYPE = "type";

    public static final String LEVEL = "level";

    public static final String NAME = "name";

    public static final String ICON = "icon";

    public static final String CREATE_UID = "create_uid";

    public static final String UPDATE_UID = "update_uid";

    public static final String CREATE_TIME = "create_time";

    public static final String UPDATE_TIME = "update_time";

    public static final String STATUS = "status";

}
