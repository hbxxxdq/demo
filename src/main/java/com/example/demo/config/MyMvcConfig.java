package com.example.demo.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Locale;
////如果你想diy一些定制化的功能，只要写这个组件，然后将它交给springboot，springboot就会帮我们自动装配
////扩展springmvc
//@Configuration
//public class MyMvcConfig implements WebMvcConfigurer {
//    //ViewResolver 实现了视图解析器接口的类，我们就可以把它看做视图解析器
//
//    @Bean
//    public ViewResolver myViewResolver() {
//        return new MyViewResolver();
//    }
//    //自定义了一个自己的视图解析器MyViewResolver
//        public static class MyViewResolver implements ViewResolver {
//
//            @Override
//            public View resolveViewName(String viewName, Locale locale) throws Exception {
//                return null;
//            }
//        }
//    }



@Configuration

public class MyMvcConfig implements WebMvcConfigurer {
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("index");
        registry.addViewController("/index.html").setViewName("index");
        //将dashboard重命名
        registry.addViewController("/main.html").setViewName("dashboard");
    }
    //自定义的国际化组件
    @Bean
    public LocaleResolver localeResolver() {
        return new MyLocaleResolver();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginHandlerInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns("/index.html","/","/user/login","/css/*","/img/**","/js/**");
    }
}