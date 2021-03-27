package com.hower.hotel.framework.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hower.hotel.common.responses.ApiResponses;
import com.hower.hotel.framework.enums.ErrorCodeEnum;
import com.hower.hotel.framework.model.ErrorCode;
import org.apache.logging.log4j.core.util.JsonUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.UnauthenticatedException;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

@CrossOrigin
public class SuperController {
    @Resource
    protected HttpServletRequest request;

    @Resource
    protected HttpServletResponse response;

    /**
     * 成功返回
     *
     * @param object
     * @return
     */
    public <T> ApiResponses<T> success(T object) {
        return ApiResponses.<T>success(response, object);
    }
    /**
     * 失败返回
     *
     * @param object
     * @return
     */
    public <T> ApiResponses<T> failure(T object) {
        return ApiResponses.<T>failure(ErrorCodeEnum.BAD_REQUEST.convert());
    }
    /**
     * 成功返回
     *
     * @return
     */
    public ApiResponses<Void> success() {
        return success(HttpStatus.OK);
    }

    /**
     * 成功返回
     *
     * @param status
     * @param object
     * @return
     */
    public <T> ApiResponses<T> success(HttpStatus status, T object) {
        return ApiResponses.<T>success(response, status, object);
    }


    /**
     * 成功返回
     * @param status
     * @return
     */
    public ApiResponses<Void> success(HttpStatus status) {
        return ApiResponses.<Void>success(response, status);
    }


    public <T> IPage<T> getPage(){
        return new Page<T>();
    }

//
//    @ExceptionHandler({UnauthenticatedException.class,AuthenticationException.class})
//    public void authenticationException(){
//        Map<String,Object> map = new HashMap<>();
//        map.put("status","401");
//        map.put("message","无操作权限");
//        System.out.println("authenticationException异常");
//        response.setHeader("Access-Control-Allow-Credentials", "true");
////            httpResponse.setHeader("Access-Control-Allow-Origin", HttpContextUtil.getOrigin());
//        response.setHeader("Access-Control-Allow-Origin", "*");
//        response.setHeader("Access-Control-Allow-Headers", "Content-Type,XFILENAME,XFILECATEGORY,XFILESIZE");
//        try {
//            PrintWriter out = response.getWriter();
//            out.write(map.toString());
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
    /**
     * 无权限操作异常处理
     */
    @ExceptionHandler({UnauthorizedException.class,AuthorizationException.class})
    public ApiResponses<Object> authorizationException(){
        return ApiResponses.failure(ErrorCodeEnum.FORBIDDEN.convert());
    }
}
