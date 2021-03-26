package com.hower.hotel.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hower.hotel.common.shiro.TokenGenerator;
import com.hower.hotel.model.entity.SysToken;
import com.hower.hotel.mapper.SysTokenMapper;
import com.hower.hotel.service.ISysTokenService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 'hower_hotel.staffdto' is not BASE TABLE 服务实现类
 * </p>
 *
 * @author Ghower
 * @since 2021-03-26
 */
@Service
public class SysTokenServiceImpl extends ServiceImpl<SysTokenMapper, SysToken> implements ISysTokenService {
    //24小时后过期
    private final static int EXPIRE = 24;

    public Map<String, Object> createToken(Integer sId) {
        Map<String, Object> result = new HashMap<>();
        //生成一个token
        String token = TokenGenerator.generateValue();
//        String token = "super_admin";
        //当前时间
        LocalDateTime now = LocalDateTime.now();
        //过期时间
        LocalDateTime expireTime = now.plusHours(EXPIRE);
        //判断是否生成过token
        SysToken sysToken = baseMapper.selectOne(
                new QueryWrapper<SysToken>().eq(SysToken.S_ID, sId));

        if (sysToken == null) {
            sysToken = new SysToken();
            sysToken.setSId(sId);
            //保存token
            sysToken.setToken(token);
            sysToken.setCreateTime(now);
            sysToken.setExpiresTime(expireTime);
        } else {
            //更新token
            sysToken.setToken(token);
            sysToken.setCreateTime(now);
            sysToken.setExpiresTime(expireTime);
        }
        baseMapper.insert(sysToken);
        result.put("token", token);
        result.put("expire", expireTime);
        return result;
    }

//    @Override
    public SysToken findByToken(String accessToken) {
        return baseMapper.selectOne(new QueryWrapper<SysToken>()
                .eq(SysToken.TOKEN, accessToken));
    }
}
