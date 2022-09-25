/*
package com.sgbg.common.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {


    private static final String[] EXCLUDE_PATHS = {
            "/auth/login",
            "/auth/logout",
            "/auth/refresh",
            "/swagger-ui",
    };

*/
/*
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(...)
                .addPathPatterns("/**")
                .excludePathPatterns(EXCLUDE_PATHS);
    }
*//*


    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowCredentials(true);
    }
}

*/
