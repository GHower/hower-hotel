package com.hower.hotel.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hower.hotel.common.responses.ApiResponses;
import com.hower.hotel.framework.controller.SuperController;
import com.hower.hotel.model.dto.DepartmentInfoDto;
import com.hower.hotel.model.entity.DepartmentInfo;
import com.hower.hotel.service.impl.DepartmentInfoServiceImpl;
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
@Api(tags = "部门信息")
@RestController
@RequestMapping("/departmentInfo")
public class DepartmentInfoRestController extends SuperController {

    @Resource
    private DepartmentInfoServiceImpl departmentInfoService;


    @ApiOperation("/{id}")
    @GetMapping("/{id}")
    public ApiResponses<DepartmentInfo> getById(@PathVariable Integer id) {
        return success(departmentInfoService.getById(id));
    }

    @ApiOperation("")
    @PostMapping("")
    @CrossOrigin
    public ApiResponses<Boolean> postById(@RequestBody DepartmentInfo departmentInfo) {
        return success(departmentInfoService.saveOrUpdate(departmentInfo));
    }

    @ApiOperation("/list")
    @GetMapping("/list")
    public ApiResponses<List<DepartmentInfo>> getList() {
        List<DepartmentInfo> infoList = departmentInfoService.list(
                new QueryWrapper<DepartmentInfo>()
                        .select("id", "name", "p_id", "status")
                        .eq("p_id", 1)
                        .eq("status", 1));
        return success(infoList);
    }

    @ApiOperation("/tree")
    @GetMapping("/tree")
    public ApiResponses<DepartmentInfoDto> getTree() {
        List<DepartmentInfoDto> list = new ArrayList<>();
        departmentInfoService.list(new QueryWrapper<DepartmentInfo>().eq("status", 1))
                .forEach(e -> list.add(e.convert(DepartmentInfoDto.class)));
        DepartmentInfoDto departmentInfos = listToTree(list.get(0), list);
        return success(departmentInfos);
    }

    private DepartmentInfoDto listToTree(DepartmentInfoDto root, List<DepartmentInfoDto> list) {
        List<DepartmentInfoDto> result = new ArrayList<>();

        list.forEach(e -> {
            if (e.getPId().equals(root.getId()) && !e.getId().equals(root.getId())) {
                result.add(e);
            }
        });
        if (result.size() > 0) {
            root.setChildren(result);
            for (DepartmentInfoDto d : result) {
                listToTree(d, list);
            }
        }
        return root;
    }
}

