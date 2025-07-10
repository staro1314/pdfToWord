package com.convert.pdftoword.config.swagger;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class RapiDocConfig  implements WebMvcConfigurer {
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/rapidoc")
                .setViewName("forward:/rapidoc/index.html");
        registry.addViewController("/rapidoc/")
                .setViewName("forward:/rapidoc/index.html");
    }
}
