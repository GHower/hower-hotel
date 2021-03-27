package com.hower.hotel.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hower.hotel.common.responses.ApiResponses;
import com.hower.hotel.framework.controller.SuperController;
import com.hower.hotel.model.entity.CustomerInfo;
import com.hower.hotel.service.impl.CustomerInfoServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;


/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author Ghower
 * @since 2021-03-21
 */
@Api(tags = {"会员信息"})
@RequestMapping(value = "/customerInfo")
@RestController
public class CustomerInfoRestController extends SuperController {
    @Resource
    private CustomerInfoServiceImpl customerInfoService;

    @GetMapping("/page")
    @ApiOperation("/page")
    public ApiResponses<IPage<CustomerInfo>> getCustomerInfoList(
            @RequestParam(defaultValue = "1",required = false)Integer current,
            @RequestParam(defaultValue = "10",required = false) Integer pageSize) {
        System.out.println(current);
        IPage<CustomerInfo> list = customerInfoService.query()
                .eq(CustomerInfo.STATUS, 1)
                .page(new Page<CustomerInfo>(current,pageSize,true));
        return success(list);
    }

    @GetMapping("/")
    @ApiOperation("/")
    public ApiResponses<CustomerInfo> getCustomerInfoByEntity(CustomerInfo customerInfo) {
        CustomerInfo info = customerInfoService.getOne(new QueryWrapper<CustomerInfo>(customerInfo));
        return success(info);
    }

    @CrossOrigin
    @PostMapping("/post")
    @ApiOperation("/post")
    @RequiresRoles("admin")
    public ApiResponses<Boolean> postCustomerInfoByEntity(@RequestBody CustomerInfo customerInfo) {
        return success(customerInfoService.saveOrUpdate(customerInfo));
    }

    @GetMapping("/search")
    @ApiOperation("/search")
    public ApiResponses<IPage<CustomerInfo>> getCustomerInfoLike(
            @RequestParam String column,
            @RequestParam String value,
            @RequestParam(defaultValue = "1",required = false)Integer current,
            @RequestParam(defaultValue = "10",required = false) Integer pageSize
    ) {
        System.out.println(column+"  "+value);
        IPage<CustomerInfo> info = customerInfoService.likePage(column,value,current,pageSize);
        return success(info);
    }
}

