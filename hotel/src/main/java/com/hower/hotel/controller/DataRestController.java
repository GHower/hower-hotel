package com.hower.hotel.controller;

import com.hower.hotel.common.responses.ApiResponses;
import com.hower.hotel.framework.controller.SuperController;
import com.hower.hotel.model.entity.OrderInfo;
import com.hower.hotel.service.impl.OrderInfoServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@Api(tags = "图表数据接口")
@RestController
@RequestMapping("/data")
public class DataRestController extends SuperController {
    @Resource
    private OrderInfoServiceImpl orderInfoService;

    @ApiOperation("/list")
    @GetMapping("/list")
    public ApiResponses<List<OrderInfo>> list() {
        return success(orderInfoService.list());
    }

}
