package com.soft1851.api.config;

import org.apache.catalina.filters.CorsFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
public class CorsConfig {
    public CorsConfig() {
    }

    @Bean
    public CorsFilter corsFilter() {
        // 1、添加cors配置信息
        CorsConfiguration config = new CorsConfiguration();
        config.addAllowedOrigin("*");
        //设置是否发送cookie信息
        config.setAllowCredentials(true);
        // 设置允许请求方式
        config.addAllowedMethod("*");
        //设置允许的header
        config.addAllowedHeader("*");
        UrlBasedCorsConfigurationSource corsSource = new UrlBasedCorsConfigurationSource();
        corsSource.registerCorsConfiguration("/**", config);
        //返回重新定义的corsSource
        return new CorsFilter(corsSource);
    }
}
