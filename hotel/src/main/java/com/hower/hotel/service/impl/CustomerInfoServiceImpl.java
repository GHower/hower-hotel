package com.hower.hotel.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hower.hotel.model.entity.CustomerInfo;
import com.hower.hotel.mapper.CustomerInfoMapper;
import com.hower.hotel.service.ICustomerInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author Ghower
 * @since 2021-03-21
 */
@Service
public class CustomerInfoServiceImpl extends ServiceImpl<CustomerInfoMapper, CustomerInfo>
        implements ICustomerInfoService {
    @Override
    public IPage<CustomerInfo> likePage(String column, String value, Integer current, Integer pageSize) {
        QueryWrapper<CustomerInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.like(column,value);
        queryWrapper.eq("status",1);
        return baseMapper.selectPage(new Page<CustomerInfo>(current,pageSize,true),queryWrapper);
    }
}
