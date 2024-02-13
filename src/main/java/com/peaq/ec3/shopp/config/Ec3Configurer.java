package com.peaq.ec3.shopp.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class Ec3Configurer implements WebMvcConfigurer {

    @Autowired
    DataInterceptor dataInterceptor;
    @Autowired
    ViewInterceptor viewInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
      /*  registry.addInterceptor(dataInterceptor)
        .addPathPatterns("/**")
        .excludePathPatterns(
                "/userInfo/login",
                "/userInfo/register",
                "/userInfo/signOut",
                "/productInfo/list",
                "/userInfo/verifyToken"
        );
        registry.addInterceptor(viewInterceptor).pathMatcher(new Ec3PathMatcher());*/
    }
}
