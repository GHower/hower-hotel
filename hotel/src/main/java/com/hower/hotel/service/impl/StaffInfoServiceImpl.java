package com.hower.hotel.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hower.hotel.common.shiro.TokenGenerator;
import com.hower.hotel.model.entity.CustomerInfo;
import com.hower.hotel.model.entity.StaffInfo;
import com.hower.hotel.mapper.StaffInfoMapper;
import com.hower.hotel.model.entity.SysToken;
import com.hower.hotel.service.IStaffInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

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

    @Resource
    private SysTokenServiceImpl sysTokenService;

    @Override
    public IPage<StaffInfo> likePage(String column, String value, Integer current, Integer pageSize) {
        QueryWrapper<StaffInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.like(column,value);
        queryWrapper.eq("status",1);
        return baseMapper.selectPage(new Page<StaffInfo>(current,pageSize,true),queryWrapper);
    }

//    @Override
    public void logout(String token) {
        SysToken byToken = sysTokenService.findByToken(token);
        //生成一个token
        token = TokenGenerator.generateValue();
        //修改token
        byToken.setToken(token);
        //使前端获取到的token失效
        sysTokenService.save(byToken);
    }
}
