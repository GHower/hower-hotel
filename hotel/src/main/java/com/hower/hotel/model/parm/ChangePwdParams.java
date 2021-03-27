package com.hower.hotel.model.parm;

import com.hower.hotel.framework.model.convert.Convert;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class ChangePwdParams extends Convert {
    private String oldPwd;
    private String newPwd;
}
