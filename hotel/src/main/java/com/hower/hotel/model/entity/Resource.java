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
@TableName("sys_resource")
public class Resource extends BaseModel {

    private static final long serialVersionUID = 1L;

    /**
     * 资源名称
     */
    private String resourceName;

    /**
     * 资源URL
     */
    private String url;

    /**
     * 备注
     */
    private String remarks;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 请求方式
     */
    private String method;


    public static final String RESOURCE_NAME = "resource_name";

    public static final String URL = "url";

    public static final String REMARKS = "remarks";

    public static final String CREATE_TIME = "create_time";

    public static final String METHOD = "method";

}
