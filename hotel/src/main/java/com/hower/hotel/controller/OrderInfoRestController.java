package com.hower.hotel.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hower.hotel.common.responses.ApiResponses;
import com.hower.hotel.framework.controller.SuperController;
import com.hower.hotel.model.dto.OrderInfoDTO;
import com.hower.hotel.model.entity.*;
import com.hower.hotel.model.parm.OrderOkParams;
import com.hower.hotel.service.impl.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
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
        OrderInfo one = orderInfoService.getOne(new QueryWrapper<OrderInfo>()
                .eq(OrderInfo.ID, id)
                .eq(OrderInfo.STATUS,"1"));
        return success(one);
    }

    @ApiOperation("/")
    @PostMapping("/")
    @CrossOrigin
    public ApiResponses<Boolean> postOrder(OrderInfo orderInfo) {
        // TODO:订单生成，检查客户信息是否存在，不存在则创建，记录当前操作员
        return success(orderInfoService.saveOrUpdate(orderInfo));
    }

    /**
     * TODO：这是一个复杂SQL，应该用存储过程优化
     *
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
        IPage<OrderInfo> infoIPage = orderInfoService.page(
                new Page<OrderInfo>().setCurrent(current),
                new QueryWrapper<>(orderInfo));
        List<OrderInfoDTO> result = new ArrayList<>();
        for (OrderInfo record : infoIPage.getRecords()) {
            result.add(order2DTO(record));
        }
        IPage<OrderInfoDTO> iPage = new Page<OrderInfoDTO>();
        iPage.setRecords(result);
        iPage.setCurrent(infoIPage.getCurrent());
        iPage.setPages(infoIPage.getPages());
        iPage.setSize(infoIPage.getSize());
        iPage.setTotal(infoIPage.getTotal());
        return success(iPage);
    }

    @ApiOperation("/byRoom")
    @GetMapping("/byRoom")
    public ApiResponses<OrderInfoDTO> getOrderByRoomId(RoomInfo roomInfo) {
        // 通过房间查出订单，只查租住中的订单，需要根据入店时间和离店时间判断
        QueryWrapper<OrderInfo> queryWrapper = new QueryWrapper<>(
                new OrderInfo().setRId(roomInfo.getId()).setStatus("2"));
//        queryWrapper.gt(OrderInfo.OUT_TIME,getDate(1) + " 14:30:00");
        OrderInfo orderInfo = orderInfoService.getOne(queryWrapper);
        System.out.println(orderInfo);
        if (orderInfo == null) {
            return success(new OrderInfoDTO());
        }
        return success(order2DTO(orderInfo));
    }

    @ApiOperation("/detail")
    @GetMapping("/detail")
    public ApiResponses<OrderInfoDTO> getOrderPage(OrderInfo orderInfo) {
        OrderInfo infoIPage = orderInfoService.getOne(new QueryWrapper<>(orderInfo));
        return success(order2DTO(infoIPage));
    }
    @ApiOperation("/today")
    @GetMapping("/today")
    public ApiResponses<List<OrderInfoDTO>> getOrderToday() {
        List<OrderInfoDTO> result = new ArrayList<>();

        List<OrderInfo> orderInfos = orderInfoService.list(new QueryWrapper<OrderInfo>()
//                .gt("in_time", getDate() + " 10:00:00")
                .lt("out_time", getDate(1) + " 14:30:00"));
        for (OrderInfo record : orderInfos) {
            result.add(order2DTO(record));
        }
        return success(result);
    }

    @ApiOperation("核销订单")
    @PostMapping("/ok")
    @RequiresRoles({"admin"})
    public ApiResponses<Boolean> postPayInfoOk(@RequestBody OrderInfo orderInfo){
        orderInfo.setStatus("1");
        orderInfoService.updateById(orderInfo);
        RoomInfo roomInfo = new RoomInfo();
        roomInfo.setId(orderInfo.getRId());
        roomInfo.setStatus(2);
        roomInfoService.updateById(roomInfo);
        return success(true);
    }


    private String getDate(){
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate now = LocalDate.now();
        return now.format(dateTimeFormatter);
    }

    private String getDate(Integer days){
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate now = LocalDate.now();
        now = now.plusDays(days);
        return now.format(dateTimeFormatter);
    }

    private OrderInfoDTO order2DTO(OrderInfo record) {
        OrderInfoDTO orderInfoDTO = new OrderInfoDTO();
        RoomInfo roomInfo = roomInfoService.getById(record.getRId());
        RoomType roomType = roomTypeService.getById(roomInfo.getTypeId());
//        StaffInfo staffInfo = staffInfoService.getById(record.getSId());
        CustomerInfo customerInfo = customerInfoService.getById(record.getCId());
        Collection<CustomerInfo> customerInfos = customerInfoService.listByIds(Arrays.asList(record.getGuestId().split(",").clone()));
        orderInfoDTO.setGuests((List<CustomerInfo>) customerInfos);
        orderInfoDTO.setOrderInfo(record);
        orderInfoDTO.setRoomType(roomType.getName());
        orderInfoDTO.setPrice(roomType.getPrice());
        orderInfoDTO.setRoomInfo(roomInfo);
//        orderInfoDTO.setStaffInfo(staffInfo);
        orderInfoDTO.setCustomerInfo(customerInfo);
        return orderInfoDTO;
    }

}

