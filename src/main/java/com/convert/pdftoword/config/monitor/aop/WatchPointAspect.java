package com.convert.pdftoword.config.monitor.aop;

import com.convert.pdftoword.config.monitor.WatchHelper;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;


@Slf4j
@Aspect
@Component
public class WatchPointAspect {

    @Pointcut("execution(public * com.convert.*.controller.*.*(..))")
    public void pointCut(){}

    @Before("pointCut()")
    public void before(){}

    @Around("pointCut()")
    public Object around(ProceedingJoinPoint point){
        WatchHelper.createStopWatch();
        Object result;
        try {
            result = point.proceed();
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    @After("pointCut()")
    public void after(){
        WatchHelper.prettyPrint();
    }

    @AfterThrowing(value = "pointCut()",throwing = "e")
    public void afterThrowing(Exception e){
        StopWatch stopWatch = WatchHelper.getStopWatch();
        if (stopWatch.isRunning()){
            stopWatch.stop();
        }
        WatchHelper.prettyPrint();
        log.error("",e);
    }
}
