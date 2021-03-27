package com.hower.hotel.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hower.hotel.common.responses.ApiResponses;
import com.hower.hotel.framework.controller.SuperController;
import com.hower.hotel.model.dto.StaffInfoDTO;
import com.hower.hotel.model.entity.StaffInfo;
import com.hower.hotel.model.entity.SysToken;
import com.hower.hotel.model.parm.ChangePwdParams;
import com.hower.hotel.model.parm.LoginParams;
import com.hower.hotel.service.impl.StaffInfoServiceImpl;
import com.hower.hotel.service.impl.SysTokenServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/current")
    @ApiOperation("当前登录的用户信息")
    public StaffInfo current(String token){
        SysToken sysToken = sysTokenService.getOne(new QueryWrapper<SysToken>().eq(SysToken.TOKEN, token));
        return staffInfoService.getById(sysToken.getSId());
    }

    @PostMapping("/login")
    @ApiOperation("登录操作")
    @CrossOrigin
    public Map<String, Object> login(
            @RequestBody  LoginParams loginParams
    ) {
        System.out.println(loginParams);
        Map<String, Object> result = new HashMap<>();
        Integer username = loginParams.getUsername();
        String password = loginParams.getPassword();
        // 通过用户名查询对象
        StaffInfo staff = staffInfoService.getById(username);
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

    @CrossOrigin
    @PostMapping("/logout")
    @ApiOperation("用户退出操作")
    public ApiResponses<Boolean> logout(){
        StaffInfo staffInfo = (StaffInfo) SecurityUtils.getSubject().getPrincipal();
        sysTokenService.removeById(staffInfo.getId());
        return success(true);
    }

    @ApiOperation("/changePwd")
    @CrossOrigin
    @PostMapping("/changePwd")
    @RequiresRoles({"admin"})
    public ApiResponses<Boolean> postChangeWord(@RequestBody ChangePwdParams changePwdParams) {
        StaffInfo staffInfo = (StaffInfo) SecurityUtils.getSubject().getPrincipal();
        System.out.println(staffInfo);
        if(staffInfo.getPassword().equals(changePwdParams.getOldPwd())){
            staffInfo.setPassword(changePwdParams.getNewPwd());
            staffInfoService.updateById(staffInfo);
        }
        this.logout();
        return success(true);
    }
}
