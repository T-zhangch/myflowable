package com.zhang.flow.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

@Slf4j
@Configuration
@EnableAsync
public class ThreadPoolConfig implements AsyncConfigurer {

    private final static int CORE_POOL_SIZE = Runtime.getRuntime().availableProcessors();

    @Override
    public Executor getAsyncExecutor(){
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(CORE_POOL_SIZE);
        executor.setMaxPoolSize(CORE_POOL_SIZE * 3);
        executor.setQueueCapacity(1000);
        executor.setAwaitTerminationSeconds(60);
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        // 线程的装饰器
        executor.setTaskDecorator(runnable -> () -> {
            System.out.println("线程执行前，资源设置。。。。");
            try {
                runnable.run();
            } catch (Exception e) {
                log.error("异常", e);
            }
            System.out.println("线程执行后，资源清理。。。。");
        });
        executor.initialize();
        return executor;
    }

    @Bean("jobExecutor")
    public ThreadPoolTaskExecutor jobExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(CORE_POOL_SIZE);
        executor.setMaxPoolSize(CORE_POOL_SIZE * 3);
        executor.setQueueCapacity(1000);
        executor.setAwaitTerminationSeconds(60);
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        // 线程的装饰器
        executor.setTaskDecorator(runnable -> () -> {
            System.out.println("线程执行前，资源设置。。。。");
            try {
                runnable.run();
            } catch (Exception e) {
                log.error("异常", e);
            }
            System.out.println("线程执行后，资源清理。。。。");
        });
        return executor;
    }
}
