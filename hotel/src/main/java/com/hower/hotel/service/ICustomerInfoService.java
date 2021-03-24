package com.hower.hotel.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.hower.hotel.model.entity.CustomerInfo;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Ghower
 * @since 2021-03-21
 */
public interface ICustomerInfoService extends IService<CustomerInfo> {
    public IPage<CustomerInfo> likePage(String column, String value, Integer current, Integer pageSize);
}
