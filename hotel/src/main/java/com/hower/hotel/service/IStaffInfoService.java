package com.hower.hotel.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.hower.hotel.model.entity.CustomerInfo;
import com.hower.hotel.model.entity.StaffInfo;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 'hower_hotel.orderdto' is not BASE TABLE 服务类
 * </p>
 *
 * @author Ghower
 * @since 2021-03-21
 */
public interface IStaffInfoService extends IService<StaffInfo> {
    public IPage<StaffInfo> likePage(String column, String value, Integer current, Integer pageSize);

}
