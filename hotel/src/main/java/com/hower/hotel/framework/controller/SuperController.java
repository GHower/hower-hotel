package com.hower.hotel.framework.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hower.hotel.common.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
}
