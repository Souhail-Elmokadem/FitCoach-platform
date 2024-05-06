package com.fitcoach.fitcoach.securityConfig;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class RessourcesConfig implements WebMvcConfigurer {
    @Override
    public void  addResourceHandlers(ResourceHandlerRegistry registry){
        registry.addResourceHandler("/content/**")
                .addResourceLocations("file:///C:/Users/souhail/IdeaProjects/FitCoach/src/main/resources/static/public/");


    }
}
