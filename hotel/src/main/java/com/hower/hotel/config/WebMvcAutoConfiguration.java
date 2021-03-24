/*
 * Copyright (c) 2018-2022 Caratacus, (caratacus@qq.com).
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of
 * this software and associated documentation files (the "Software"), to deal in
 * the Software without restriction, including without limitation the rights to
 * use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of
 * the Software, and to permit persons to whom the Software is furnished to do so,
 * subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS
 * FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR
 * COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER
 * IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN
 * CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */
package com.hower.hotel.config;

import java.util.List;

//import com.jifi.common.spring.CrownHandlerExceptionResolver;
//import com.jifi.common.spring.IEnumConverterFactory;
//import com.jifi.common.spring.validator.ValidatorCollectionImpl;
//import com.jifi.common.undertow.UndertowServerFactoryCustomizer;
//import com.jifi.common.utils.JacksonUtils;
//import com.jifi.framework.aspect.LogRecordAspect;

import com.hower.hotel.common.undertow.UndertowServerFactoryCustomizer;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.SpringValidatorAdapter;
import org.springframework.web.context.request.RequestContextListener;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import io.undertow.Undertow;

/**
 * <p>
 * Spring Boot 配置
 * </p>
 */
@Configuration
public class WebMvcAutoConfiguration implements WebMvcConfigurer {

    //    @Bean
//    public LogRecordAspect logRecordAspect() {
//        return new LogRecordAspect();
//    }
//
//    @Override
//    public Validator getValidator() {
//        return new SpringValidatorAdapter(new ValidatorCollectionImpl());
//    }
//
//    @Override
//    public void addFormatters(FormatterRegistry registry) {
//        registry.addConverterFactory(new IEnumConverterFactory());
//    }
//
    @Bean
    @ConditionalOnClass(Undertow.class)
    public UndertowServerFactoryCustomizer undertowServerFactoryCustomizer() {
        return new UndertowServerFactoryCustomizer();
    }

    @Bean
    @ConditionalOnMissingBean(RequestContextListener.class)
    public RequestContextListener requestContextListener() {
        return new RequestContextListener();
    }
//
//    @Override
//    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
//        converters.forEach(JacksonUtils.wrapperObjectMapper());
//    }
//
//    @Override
//    public void configureHandlerExceptionResolvers(List<HandlerExceptionResolver> exceptionResolvers) {
//        exceptionResolvers.add(new CrownHandlerExceptionResolver());
//    }

}
