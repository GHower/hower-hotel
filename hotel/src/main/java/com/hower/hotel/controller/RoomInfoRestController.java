package com.hower.hotel.controller;


import com.hower.hotel.common.responses.ApiResponses;
import com.hower.hotel.framework.controller.SuperController;
import com.hower.hotel.model.entity.RoomInfo;
import com.hower.hotel.service.impl.RoomInfoServiceImpl;
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
 * 'hower_hotel.orderdto' is not BASE TABLE 前端控制器
 * </p>
 *
 * @author Ghower
 * @since 2021-03-21
 */
@Api("房间信息")
@RestController
@RequestMapping("/roomInfo")
public class RoomInfoRestController extends SuperController {
    @Resource
    private RoomInfoServiceImpl roomInfoService;

    @ApiOperation("/{id}")
    @GetMapping("/{id}")
    public ApiResponses<RoomInfo> getById(@PathVariable Integer id) {
        return success(roomInfoService.getById(id));
    }
    @ApiOperation("/list")
    @GetMapping("/list")
    public ApiResponses<List<RoomInfo>> getList() {
        return success(roomInfoService.list());
    }
//    @ApiOperation("/page")
//    @GetMapping("/page")
//    public ApiResponses<List<RoomInfo>> getInfoPage() {
//        return success(roomInfoService.list());
//    }
}

