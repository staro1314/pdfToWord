package com.convert.pdftoword.config.thread;

import com.convert.pdftoword.config.thread.properity.VirtualThreadProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

@Configuration
public class VirtualTreadConfig {

    @Bean
    public VirtualThreadProperties virtualThreadProperties(){
        return new VirtualThreadProperties();
    }

    @Bean(name = "virtualExecutorService")
    public ExecutorService virtualExecutorService(VirtualThreadProperties properties){
        ThreadFactory factory = Thread.ofVirtual()
                .inheritInheritableThreadLocals(false)
                .name(properties.getThreadNamePrefix(), 0)
                .uncaughtExceptionHandler((t, e)
                        -> properties.getLogger().error(STR."virtual thread\{t.getName()}fail", e))
                .factory();
        return Executors.newThreadPerTaskExecutor(factory);
    }
}
