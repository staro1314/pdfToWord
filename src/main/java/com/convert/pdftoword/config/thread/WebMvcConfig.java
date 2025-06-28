package com.convert.pdftoword.config.thread;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.web.embedded.tomcat.TomcatProtocolHandlerCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.ExecutorService;

@Configuration
public class WebMvcConfig {

    @Autowired
    @Qualifier("virtualExecutorService")
    private ExecutorService virtualExecutorService;

    @Bean
    public TomcatProtocolHandlerCustomizer<?> protocolHandlerCustomizer(){
        return protocolHandler -> {
            protocolHandler.setExecutor(virtualExecutorService);
        };
    }
}
