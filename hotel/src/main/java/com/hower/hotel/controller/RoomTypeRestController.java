package com.hower.hotel.controller;


import com.hower.hotel.common.responses.ApiResponses;
import com.hower.hotel.framework.controller.SuperController;
import com.hower.hotel.model.entity.RoomType;
import com.hower.hotel.service.impl.RoomTypeServiceImpl;
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
@Api(tags="房间类型")
@RestController
@RequestMapping("/roomType")
public class RoomTypeRestController extends SuperController {
    @Resource
    private RoomTypeServiceImpl roomTypeService;

    @ApiOperation("/{id}")
    @GetMapping("/{id}")
    public ApiResponses<RoomType> getById(@PathVariable Integer id) {
        return success(roomTypeService.getById(id));
    }

    @ApiOperation("/list")
    @GetMapping("/list")
    public ApiResponses<List<RoomType>> list() {
        return success(roomTypeService.list());
    }

}

