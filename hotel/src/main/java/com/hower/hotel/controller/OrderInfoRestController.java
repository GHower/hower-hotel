package com.hower.hotel.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hower.hotel.common.responses.ApiResponses;
import com.hower.hotel.framework.controller.SuperController;
import com.hower.hotel.model.dto.OrderInfoDTO;
import com.hower.hotel.model.entity.*;
import com.hower.hotel.service.impl.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author Ghower
 * @since 2021-03-21
 */
@Api(tags = "订单信息")
@RestController
@RequestMapping("/orderInfo")
public class OrderInfoRestController extends SuperController {
    @Resource
    private OrderInfoServiceImpl orderInfoService;
    @Resource
    private CustomerInfoServiceImpl customerInfoService;
    @Resource
    private StaffInfoServiceImpl staffInfoService;
    @Resource
    private RoomTypeServiceImpl roomTypeService;
    @Resource
    private RoomInfoServiceImpl roomInfoService;


    @ApiOperation("/{id}")
    @GetMapping("/{id}")
    public ApiResponses<OrderInfo> getById(@PathVariable Integer id) {
        return success(orderInfoService.getById(id));
    }

    @ApiOperation("/")
    @PostMapping("/")
    public ApiResponses<Boolean> getOrderById(OrderInfo orderInfo) {
        return success(orderInfoService.saveOrUpdate(orderInfo));
    }

    /**
     * TODO：这是一个复杂SQL，应该用存储过程优化
     * @param orderInfo
     * @param current
     * @param pageSize
     * @return
     */
    @ApiOperation("/page")
    @GetMapping("/page")
    public ApiResponses<IPage<OrderInfoDTO>> getOrderPage(
            OrderInfo orderInfo,
            @RequestParam(required = false, defaultValue = "1") Integer current,
            @RequestParam(required = false, defaultValue = "10") Integer pageSize) {
        IPage<OrderInfo> infoIPage = orderInfoService.page(new Page<OrderInfo>()
                .setCurrent(current),new QueryWrapper<>(orderInfo));
        List<OrderInfoDTO> result = new ArrayList<>();
        for (OrderInfo record : infoIPage.getRecords()) {
            OrderInfoDTO orderInfoDTO = new OrderInfoDTO();
            RoomInfo roomInfo = roomInfoService.getById(record.getRId());
            RoomType roomType = roomTypeService.getById(roomInfo.getTypeId());
            StaffInfo staffInfo = staffInfoService.getById(record.getSId());
            CustomerInfo customerInfo = customerInfoService.getById(record.getCId());
            orderInfoDTO.setOrderInfo(record);
            orderInfoDTO.setRoomType(roomType.getName());
            orderInfoDTO.setRoomInfo(roomInfo);
            orderInfoDTO.setStaffInfo(staffInfo);
            orderInfoDTO.setCustomerInfo(customerInfo);
            result.add(orderInfoDTO);
        }
        IPage<OrderInfoDTO> iPage = new Page<OrderInfoDTO>();
        iPage.setRecords(result);
        iPage.setCurrent(infoIPage.getCurrent());
        iPage.setPages(infoIPage.getPages());
        iPage.setSize(infoIPage.getSize());
        iPage.setTotal(infoIPage.getTotal());
        return success(iPage);
    }
    @ApiOperation("/detail")
    @GetMapping("/detail")
    public ApiResponses<OrderInfoDTO> getOrderPage(OrderInfo orderInfo) {
        OrderInfo infoIPage = orderInfoService.getOne(new QueryWrapper<>(orderInfo));
        OrderInfoDTO orderInfoDTO = new OrderInfoDTO();
        RoomInfo roomInfo = roomInfoService.getById(infoIPage.getRId());
        RoomType roomType = roomTypeService.getById(roomInfo.getTypeId());
        StaffInfo staffInfo = staffInfoService.getById(infoIPage.getSId());
        CustomerInfo customerInfo = customerInfoService.getById(infoIPage.getCId());
        orderInfoDTO.setOrderInfo(infoIPage);
        orderInfoDTO.setRoomType(roomType.getName());
        orderInfoDTO.setRoomInfo(roomInfo);
        orderInfoDTO.setStaffInfo(staffInfo);
        orderInfoDTO.setCustomerInfo(customerInfo);
        return success(orderInfoDTO);
    }
}

