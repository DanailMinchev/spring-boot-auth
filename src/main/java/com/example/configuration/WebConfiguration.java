package com.example.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class WebConfiguration extends WebMvcConfigurerAdapter {

    @Override
    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
        // The order of content negotiation:
        // 1. Path extension in the request URL (for example: ".json" or ".xml")
        // 2. Query parameter "format" in the request URL (for example: "?format=json")
        // 3. HTTP "Accept" header (for example: "application/json")
        configurer
            .favorPathExtension(true)
            .favorParameter(true)
            .parameterName("format")
            .ignoreAcceptHeader(false);
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**");
    }

}
