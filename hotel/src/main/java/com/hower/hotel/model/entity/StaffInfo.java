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
@TableName("staff_info")
public class StaffInfo extends BaseModel {

    private static final long serialVersionUID = 1L;

    /**
     * 部门ID
     */
    private Integer depId;

    /**
     * 员工姓名
     */
    private String name;

    /**
     * 员工证件号码
     */
    private String identity;

    /**
     * 员工邮箱，可不设置
     */
    private String email;

    /**
     * 员工登录密码
     */
    private String password;

    /**
     * 员工联系方式
     */
    private String contact;

    /**
     * 状态
     */
    private String status;

    /**
     * 上班的班次,0-白班,1-夜班
     */
    private Integer shift;

    /**
     * 创建者ID
     */
    private Integer createUid;

    /**
     * 修改者ID
     */
    private Integer updateUid;
    /**
     * 性别
     */
    private Integer sex;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 修改时间
     */
    private LocalDateTime updateTime;


    public static final String DEP_ID = "dep_id";

    public static final String NAME = "name";

    public static final String IDENTITY = "identity";

    public static final String EMAIL = "email";

    public static final String PASSWORD = "password";

    public static final String CONTACT = "contact";

    public static final String STATUS = "status";

    public static final String SHIFT = "shift";
    public static final String SEX = "sex";

    public static final String CREATE_UID = "create_uid";

    public static final String UPDATE_UID = "update_uid";

    public static final String CREATE_TIME = "create_time";

    public static final String UPDATE_TIME = "update_time";

}
