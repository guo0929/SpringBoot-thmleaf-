//package com.guo.blog.config;
//
//import com.guo.blog.interceptor.UserLoginInterceptor;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
//import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//
//@Configuration
//public class LoginConfig implements WebMvcConfigurer {
//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        //注册TestInterceptor拦截器
//        InterceptorRegistration registration = registry.addInterceptor(new UserLoginInterceptor());
//        registration.addPathPatterns("/**"); //所有路径都被拦截
//        registration.excludePathPatterns(    //添加不拦截路径
//                "/",                //首页路径
//                "/blog/toLogin",                    //登录路径
//                "/blog/login",
//                "/**/*.html",                 //html静态资源
//                "/**/*.js",                  //js静态资源
//                "/**/*.css"                  //css静态资源
//        );
//    }
//}
//
