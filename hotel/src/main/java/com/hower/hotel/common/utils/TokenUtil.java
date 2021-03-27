package com.hower.hotel.common.utils;

import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

/**
 * token工具类
 */
public class TokenUtil {
    /**
     * 获取请求的token
     */
    public static String getRequestToken(HttpServletRequest httpRequest) {

        //从header中获取token
        String token = httpRequest.getHeader("token");
        //如果header中不存在token，则从参数中获取token
        if (StringUtils.isBlank(token)) {
            token = httpRequest.getParameter("token");
        }
//        else if(httpRequest.getCookies().length>0){
//            for (Cookie cookie : httpRequest.getCookies()) {
//                if (cookie.getName().equals("token")){
//                    token=cookie.getValue();
//                }
//            }
//        }
        return token;
    }
}
