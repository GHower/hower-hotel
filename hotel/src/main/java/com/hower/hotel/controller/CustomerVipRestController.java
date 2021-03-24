package com.hower.hotel.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hower.hotel.common.responses.ApiResponses;
import com.hower.hotel.framework.controller.SuperController;
import com.hower.hotel.model.entity.CustomerVip;
import com.hower.hotel.service.impl.CustomerVipServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Ghower
 * @since 2021-03-21
 */
@Api(tags="会员类别")
@RestController
@RequestMapping("/customerVip")
public class CustomerVipRestController extends SuperController {

    @Resource
    private CustomerVipServiceImpl customerVipService;
    @ApiOperation("/{id}")
    @GetMapping("/{id}")
    public ApiResponses<CustomerVip> getById(@PathVariable Integer id){
        CustomerVip customerVip = customerVipService.getById(id);
        return  success(customerVip);
    }
    @ApiOperation("/list")
    @GetMapping("/list")
    public ApiResponses<List<CustomerVip>> getList(){
        List<CustomerVip> customerVip = customerVipService.list(new QueryWrapper<CustomerVip>()
                .eq("status",1));
        return  success(customerVip);
    }


}

