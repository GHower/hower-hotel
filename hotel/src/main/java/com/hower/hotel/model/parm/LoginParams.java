package com.hower.hotel.model.parm;

import com.hower.hotel.framework.model.convert.Convert;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class LoginParams extends Convert {
    private Integer username;
    private String password;
}
