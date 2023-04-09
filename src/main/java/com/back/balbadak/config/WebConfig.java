package com.back.balbadak.config;

import com.back.balbadak.resolver.FilePathResolver;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.nio.file.Path;
import java.nio.file.Paths;

@Configuration
public class WebConfig implements WebMvcConfigurer {
	@Value("${file.resource.path}")
	private String resourcePath;

	@Value("${file.upload.path}")
	private String fileUploadPath;

	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**").allowedOrigins("http://localhost:3000", "http://172.31.99.238:3000")
				.allowedMethods("GET", "POST").maxAge(3000);
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
//		registry.addResourceHandler(fileUploadPath)
//				.addResourceLocations(resourcePath)
//				.resourceChain(true)
//				.addResolver(new FilePathResolver());

		registry.addResourceHandler(fileUploadPath)
				.addResourceLocations(resourcePath);
	}

}
