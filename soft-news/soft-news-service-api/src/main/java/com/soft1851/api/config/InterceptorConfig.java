package com.soft1851.api.config;

import com.soft1851.api.interceptors.PassportInterceptor;
import com.soft1851.api.interceptors.UserActiveInterceptor;
import com.soft1851.api.interceptors.UserTokenInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer {
    @Bean
    public PassportInterceptor passportInterceptor() {
        return new PassportInterceptor();
    }

    @Bean
    public UserTokenInterceptor userTokenInterceptor() {
        return new UserTokenInterceptor();
    }

    @Bean
    public UserActiveInterceptor userActiveInterceptor() {
        return new UserActiveInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
//        注册拦截器，添加拦截路由
        registry.addInterceptor(passportInterceptor()).addPathPatterns("/passport/smsCode");

        registry.addInterceptor(userTokenInterceptor())
                .addPathPatterns("/user/getUserInfo")
                .addPathPatterns("/user/updateUserInfo");

        registry.addInterceptor(userActiveInterceptor())
                .addPathPatterns("/user/fans/follow");

    }
}
