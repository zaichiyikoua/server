package com.program.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.program.interceptor.Interceptor;

@Configuration
public class Config	implements WebMvcConfigurer {
	//跨域部分
	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/program/**")
		.allowedHeaders("*")
		.allowedMethods("*")
		.maxAge(1800)
		.allowedOrigins("http://localhost:8083");
	}
//	//拦截器
//	@Override
//	public void addInterceptors(InterceptorRegistry registry) {
//		registry.addInterceptor(new Interceptor()).addPathPatterns("/**")
//				.addPathPatterns("/login");
//	}
	
}
