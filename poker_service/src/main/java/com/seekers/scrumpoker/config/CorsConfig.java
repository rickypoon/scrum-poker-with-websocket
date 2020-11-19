package com.seekers.scrumpoker.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig {
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/estimate").allowedOrigins("http://localhost:3000");
                registry.addMapping("/createStory").allowedOrigins("http://localhost:3000");
                registry.addMapping("/createUser").allowedOrigins("http://localhost:3000");
                registry.addMapping("/getAllStory").allowedOrigins("http://localhost:3000");
                registry.addMapping("/websocket").allowedOrigins("http://localhost:3000");
            }
        };
    }
}
