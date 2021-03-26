package com.hower.hotel.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hower.hotel.model.entity.SysPermission;
import com.hower.hotel.mapper.SysPermissionMapper;
import com.hower.hotel.model.entity.SysRolePermission;
import com.hower.hotel.service.ISysPermissionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 'hower_hotel.staffdto' is not BASE TABLE 服务实现类
 * </p>
 *
 * @author Ghower
 * @since 2021-03-26
 */
@Service
public class SysPermissionServiceImpl extends ServiceImpl<SysPermissionMapper, SysPermission> implements ISysPermissionService {
    @Resource
    private SysRolePermissionServiceImpl rolePermissionService;

    public List<SysPermission> getByRoleId(Integer rId){
        List<SysRolePermission> sysRolePermissions = rolePermissionService.list(new QueryWrapper<SysRolePermission>()
                .eq(SysRolePermission.ROLE_ID, rId));
        List<Integer> ids = new ArrayList<>();
        sysRolePermissions.forEach(e->{
            ids.add(e.getPermissionId());
        });
        return baseMapper.selectBatchIds(ids);
    }
}
