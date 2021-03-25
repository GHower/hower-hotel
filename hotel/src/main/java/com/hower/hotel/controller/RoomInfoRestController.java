package com.hower.hotel.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hower.hotel.common.responses.ApiResponses;
import com.hower.hotel.framework.controller.SuperController;
import com.hower.hotel.model.dto.RoomInfoDTO;
import com.hower.hotel.model.entity.RoomInfo;
import com.hower.hotel.model.entity.RoomType;
import com.hower.hotel.service.impl.RoomInfoServiceImpl;
import com.hower.hotel.service.impl.RoomTypeServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 'hower_hotel.orderdto' is not BASE TABLE 前端控制器
 * </p>
 *
 * @author Ghower
 * @since 2021-03-21
 */
@Api(tags="房间信息")
@RestController
@RequestMapping("/roomInfo")
public class RoomInfoRestController extends SuperController {
    @Resource
    private RoomInfoServiceImpl roomInfoService;
    @Resource
    private RoomTypeServiceImpl roomTypeService;

    @ApiOperation("/{id}")
    @GetMapping("/{id}")
    public ApiResponses<RoomInfo> getById(@PathVariable Integer id) {
        return success(roomInfoService.getById(id));
    }
    @ApiOperation("/list")
    @GetMapping("/list")
    public ApiResponses<List<RoomInfo>> getList(RoomInfo roomInfo) {
        return success(roomInfoService.list(new QueryWrapper<>(roomInfo)));
    }
    @ApiOperation("/listDTO")
    @GetMapping("/listDTO")
    public ApiResponses<List<RoomInfoDTO>> getDTOList(RoomInfo roomInfo) {
        List<RoomInfoDTO> result=new ArrayList<>();
        List<RoomType> typeList = roomTypeService.list();
        List<RoomInfo> infoList = roomInfoService.list();
        for (RoomType roomType : typeList) {
            RoomInfoDTO roomInfoDTO = roomType.convert(RoomInfoDTO.class);
            List<RoomInfo> collect = infoList.stream()
                    .filter(e -> e.getTypeId().equals(roomType.getId()))
                    .collect(Collectors.toList());
            roomInfoDTO.setRoomInfo(collect);
            result.add(roomInfoDTO);
        }
        return success(result);
    }
//    @ApiOperation("/page")
//    @GetMapping("/page")
//    public ApiResponses<List<RoomInfo>> getInfoPage() {
//        return success(roomInfoService.list());
//    }
}

