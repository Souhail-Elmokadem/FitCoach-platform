package com.fitcoach.fitcoach.securityConfig;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.util.ResourceUtils;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.io.File;
import java.io.IOException;

@Configuration
public class RessourcesConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        if (isRunningInJar()) {
            // When running in a JAR, resources are in the classpath
            registry.addResourceHandler("/content/**")
                    .addResourceLocations("classpath:/static/public/");
        } else {
            // When running from IDE or exploded WAR, use the file system location
            registry.addResourceHandler("/content/**")
                    .addResourceLocations("file:///C:/Users/souhail/IdeaProjects/FitCoach/src/main/resources/static/public/");
        }
    }

    private boolean isRunningInJar() {
        try {
            // Attempt to load a classpath resource
            Resource resource = new ClassPathResource("static/public/");
            File file = resource.getFile();
            return false; // If we can access the file, we are not running in a JAR
        } catch (IOException e) {
            // If we get an IOException, we are running from a JAR
            return true;
        }
    }
}
