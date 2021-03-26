package com.hower.hotel.controller;

import com.hower.hotel.common.responses.ApiResponses;
import com.hower.hotel.framework.controller.SuperController;
import com.hower.hotel.model.entity.StaffInfo;
import com.hower.hotel.model.parm.LoginParams;
import com.hower.hotel.service.impl.StaffInfoServiceImpl;
import com.hower.hotel.service.impl.SysTokenServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@Api(tags = "登录相关接口")
@RestController
@RequestMapping("/login")
public class LoginController extends SuperController {

    @Resource
    private StaffInfoServiceImpl staffInfoService;
    @Resource
    private SysTokenServiceImpl sysTokenService;

    @PostMapping("/login")
    @ApiOperation("登录操作")
    public Map<String, Object> login(
            @RequestBody  LoginParams loginParams
    ) {
        Map<String, Object> result = new HashMap<>();
        Integer username = loginParams.getUsername();
        String password = loginParams.getPassword();
        // 通过用户名查询对象
        StaffInfo staff = staffInfoService.getById(username);
//        System.out.println(staff);
        if (staff == null || !staff.getPassword().equals(password)) {
            result.put("status", 400);
            result.put("msg", "账号或密码有误");
        } else {
            //生成token，并保存到数据库
            result = sysTokenService.createToken(staff.getId());
            result.put("status", 200);
            result.put("msg", "登陆成功");
        }
        return result;
    }

    @PostMapping("/logout")
    @ApiOperation("用户退出操作")
    public ApiResponses<Map<String,String>> logout(){
        HashMap<String, String> result = new HashMap<>();
        return success(result);
    }
}
