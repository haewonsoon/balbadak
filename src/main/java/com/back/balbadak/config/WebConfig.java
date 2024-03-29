package com.back.balbadak.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
	@Value("${url.front.local}")
	private String frontLocalUrl;

	@Value("${url.front.dev}")
	private String frontDevUrl;

	@Value("${file.resource.path}")
	private String resourcePath;

	@Value("${file.upload.path}")
	private String fileUploadPath;

	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**").allowedOrigins(frontLocalUrl, frontDevUrl)
				.allowedMethods("GET", "POST").maxAge(3000);
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler(fileUploadPath + "**")
				.addResourceLocations(resourcePath);
	}

}
