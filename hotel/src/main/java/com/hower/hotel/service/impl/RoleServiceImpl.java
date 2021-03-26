package com.hower.hotel.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hower.hotel.model.entity.Role;
import com.hower.hotel.mapper.RoleMapper;
import com.hower.hotel.model.entity.StaffRole;
import com.hower.hotel.service.IRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.management.relation.RoleInfo;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 'hower_hotel.staffdto' is not BASE TABLE 服务实现类
 * </p>
 *
 * @author Ghower
 * @since 2021-03-21
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements IRoleService {
    @Resource
    private StaffRoleServiceImpl staffRoleService;

    public List<Role> getByUId(Integer uId){
        List<StaffRole> list = staffRoleService.list(new QueryWrapper<StaffRole>()
                .eq(StaffRole.UID, uId));
        List<Integer> ids = new ArrayList<>();

        list.forEach(e->{
            ids.add(e.getRoleId());
        });
        return baseMapper.selectBatchIds(ids);
    }
}
