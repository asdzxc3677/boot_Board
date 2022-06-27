package com.its.board.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer { //인터페이스 구현 정의
    private String connectPath = "/upload/**"; // 업로드라는 모든것들이 실행되었을때
    private String resourcePath = "file:///D:/springboot_img/"; // 저장경로 설정

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry){
        registry.addResourceHandler(connectPath)
                .addResourceLocations(resourcePath);
    }
}
