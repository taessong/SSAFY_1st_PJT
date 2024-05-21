package com.runonground.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

	@Value("${file.upload-dir}")
	private String uploadDir;

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		// 정적 리소스 핸들러 설정
		registry.addResourceHandler("/**").addResourceLocations("classpath:/static/");

		// 업로드된 파일 핸들러 설정
		registry.addResourceHandler("/uploads/**").addResourceLocations("file:" + uploadDir + "/");
		// 포스트 이미지 핸들러 설정하기
		registry.addResourceHandler("/postUploads/**").addResourceLocations("file:postUploads/");
	}

	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**").allowedOrigins("http://localhost:5173") // 여기에 클라이언트의 도메인을 추가
				.allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS").allowedHeaders("*").allowCredentials(true);
	}
}
