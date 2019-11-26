package com.program.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

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

}
