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
 * @since 2021-03-26
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("sys_token")
public class SysToken extends BaseModel {

    private static final long serialVersionUID = 1L;

    /**
     * token
     */
    private String token;

    /**
     * 用户id
     */
    private Integer sId;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 过期时间
     */
    private LocalDateTime expiresTime;


    public static final String TOKEN = "token";

    public static final String S_ID = "s_id";

    public static final String CREATE_TIME = "create_time";

    public static final String EXPIRES_TIME = "expires_time";

}
