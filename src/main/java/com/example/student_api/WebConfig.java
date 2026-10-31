package com.example.student_api;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // This tells Spring Boot where the physical "uploads" folder is
        // and maps it to the /uploads/** URL path
        registry.addResourceHandler("/uploads/**")
                .addResourceLocations("file:uploads/");
    }
}
