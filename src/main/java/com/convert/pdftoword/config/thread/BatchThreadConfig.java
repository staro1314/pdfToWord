package com.convert.pdftoword.config.thread;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.SimpleAsyncTaskExecutor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Configuration
//@EnableBatchProcessing
public class BatchThreadConfig {

    @Bean(name = "batchExecutorService")
    public ExecutorService batchVirtualThreadExecutor(){
        return Executors.newVirtualThreadPerTaskExecutor();
    }

    @Bean
    public SimpleAsyncTaskExecutor batchTaskExecutor(){
        return new SimpleAsyncTaskExecutor("spring-batch-vt-"){
            @Override
            protected void doExecute(Runnable task) {
                batchVirtualThreadExecutor().execute(task);
            }
        };
    }
}
