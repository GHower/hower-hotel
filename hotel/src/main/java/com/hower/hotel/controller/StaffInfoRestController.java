package com.hower.hotel.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hower.hotel.common.responses.ApiResponses;
import com.hower.hotel.framework.controller.SuperController;
import com.hower.hotel.model.entity.CustomerInfo;
import com.hower.hotel.model.entity.StaffInfo;
import com.hower.hotel.service.impl.StaffInfoServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 'hower_hotel.orderdto' is not BASE TABLE 前端控制器
 * </p>
 *
 * @author Ghower
 * @since 2021-03-21
 */
@Api(tags = "员工表")
@RestController
@RequestMapping("/staffInfo")
public class StaffInfoRestController extends SuperController {
    @Resource
    private StaffInfoServiceImpl staffInfoService;

    @ApiOperation("/{id}")
    @GetMapping("/{id}")
    public ApiResponses<StaffInfo> getById(@PathVariable Integer id) {
        return success(staffInfoService.getById(id));
    }

    @ApiOperation("/list")
    @GetMapping("/list")
    public ApiResponses<List<StaffInfo>> list() {
        return success(staffInfoService.list());
    }

    @ApiOperation("/page")
    @GetMapping("/page")
    public ApiResponses<IPage<StaffInfo>> getByPage(StaffInfo staffInfo, Integer current) {
        return success(staffInfoService.page(new Page<StaffInfo>()
                        .setCurrent(current),
                new QueryWrapper<StaffInfo>(staffInfo)));
    }
    @GetMapping("/search")
    @ApiOperation("/search")
    public ApiResponses<IPage<StaffInfo>> getCustomerInfoLike(
            @RequestParam String column,
            @RequestParam String value,
            @RequestParam(defaultValue = "1",required = false)Integer current,
            @RequestParam(defaultValue = "10",required = false) Integer pageSize
    ) {
        System.out.println(column+"  "+value);
        IPage<StaffInfo> info = staffInfoService.likePage(column,value,current,pageSize);
        return success(info);
    }
}

