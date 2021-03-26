package com.hower.hotel.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hower.hotel.common.responses.ApiResponses;
import com.hower.hotel.framework.controller.SuperController;
import com.hower.hotel.model.dto.OrderPayInfoDTO;
import com.hower.hotel.model.entity.OrderPayInfo;
import com.hower.hotel.model.entity.StaffInfo;
import com.hower.hotel.service.impl.OrderPayInfoServiceImpl;
import com.hower.hotel.service.impl.StaffInfoServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Ghower
 * @since 2021-03-26
 */
@Api(tags="订单的支付信息")
@RestController
@RequestMapping("/orderPayInfo")
public class OrderPayInfoRestController extends SuperController {
    @Resource
    private OrderPayInfoServiceImpl orderPayInfoService;
    @Resource
    private StaffInfoServiceImpl staffInfoService;

    @ApiOperation("获取订单支付信息")
    @GetMapping("/")
    public ApiResponses<List<OrderPayInfoDTO>> getPayByOrderId(Integer oId){
        QueryWrapper<OrderPayInfo> queryWrapper = new QueryWrapper<OrderPayInfo>()
                .eq(OrderPayInfo.O_ID, oId);
        List<OrderPayInfoDTO> list = new ArrayList<>();
        orderPayInfoService.list(queryWrapper).forEach(e->{
            OrderPayInfoDTO convert = e.convert(OrderPayInfoDTO.class);
            StaffInfo staffInfo = staffInfoService.getById(e.getSId());
            convert.setStaffInfo(staffInfo);
            list.add(convert);
        });
        return success(list);
    }
}

