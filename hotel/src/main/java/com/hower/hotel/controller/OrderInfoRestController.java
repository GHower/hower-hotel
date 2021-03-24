package com.hower.hotel.controller;


import com.hower.hotel.common.responses.ApiResponses;
import com.hower.hotel.framework.controller.SuperController;
import com.hower.hotel.model.entity.OrderInfo;
import com.hower.hotel.service.impl.OrderInfoServiceImpl;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

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
@RestController
@RequestMapping("/orderInfo")
public class OrderInfoRestController extends SuperController {
    @Resource
    private OrderInfoServiceImpl orderInfoService;

    @ApiOperation("/{id}")
    @GetMapping("/{id}")
    public ApiResponses<OrderInfo> getById(@PathVariable Integer id) {
        return success(orderInfoService.getById(id));
    }
    @ApiOperation("/")
    @PostMapping("/")
    public ApiResponses<Boolean> getById(OrderInfo orderInfo) {
        return success(orderInfoService.saveOrUpdate(orderInfo) );
    }

    @ApiOperation("/list")
    @GetMapping("/list")
    public ApiResponses<List<OrderInfo>> list() {
        return success(orderInfoService.list());
    }

}

