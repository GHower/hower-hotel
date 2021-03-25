package com.hower.hotel.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hower.hotel.model.entity.CustomerInfo;
import com.hower.hotel.model.entity.StaffInfo;
import com.hower.hotel.mapper.StaffInfoMapper;
import com.hower.hotel.service.IStaffInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 'hower_hotel.orderdto' is not BASE TABLE 服务实现类
 * </p>
 *
 * @author Ghower
 * @since 2021-03-21
 */
@Service
public class StaffInfoServiceImpl extends ServiceImpl<StaffInfoMapper, StaffInfo> implements IStaffInfoService {
    @Override
    public IPage<StaffInfo> likePage(String column, String value, Integer current, Integer pageSize) {
        QueryWrapper<StaffInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.like(column,value);
        queryWrapper.eq("status",1);
        return baseMapper.selectPage(new Page<StaffInfo>(current,pageSize,true),queryWrapper);
    }
}
