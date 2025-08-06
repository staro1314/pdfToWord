package com.convert.pdftoword.function;

import com.convert.pdftoword.config.monitor.WatchHelper;
import com.convert.pdftoword.function.inter.TriConsumer;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.function.TriFunction;
import org.springframework.util.StopWatch;

import java.util.Arrays;
import java.util.concurrent.Callable;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;

@Slf4j
public class FunctionHelper {
    /**
     * 无入参、无返回值
     * @param runnable 待执行方法接口
     * @param onError 异常处理方法接口
     */
    public static void doWitch(Runnable runnable, Consumer<Exception> onError){
        String currentStack = getCurrentStack(Thread.currentThread().getStackTrace());
        StopWatch stopWatch = WatchHelper.getStopWatch();
        try {
            stopWatch.start(currentStack);
            runnable.run();
        }catch (Exception e){
            if (onError != null){
                onError.accept(e);
            }else {
                log.error("方法执行异常！",e);
            }
        }finally {
            stopWatch.stop();
        }
    }

    /**
     * 一个入参，无返回值
     * @param t 入参
     * @param consumer 执行方法接口
     * @param onError 异常处理接口
     * @param <T> 入参类型
     */
    public static <T> void doWitch(T t,Consumer<T> consumer,Consumer<Exception> onError){
        String currentStack = getCurrentStack(Thread.currentThread().getStackTrace());
        StopWatch stopWatch = WatchHelper.getStopWatch();
        try {
            stopWatch.start(currentStack);
            consumer.accept(t);
        }catch (Exception e){
            if (onError != null){
                onError.accept(e);
            }else {
                log.error("方法执行异常！",e);
            }
        }finally {
            stopWatch.stop();
        }
    }

    /**
     * 两个入参，无返回值
     * @param t1 入参1
     * @param t2 入参2
     * @param consumer 执行方法接口
     * @param onError 异常处理接口
     * @param <T1> 入参一类型
     * @param <T2> 入参二类型
     */
    public static <T1,T2> void doWitch(T1 t1, T2 t2, BiConsumer<T1,T2> consumer, Consumer<Exception> onError){
        String currentStack = getCurrentStack(Thread.currentThread().getStackTrace());
        StopWatch stopWatch = WatchHelper.getStopWatch();
        try {
            stopWatch.start(currentStack);
            consumer.accept(t1,t2);
        }catch (Exception e){
            if (onError != null){
                onError.accept(e);
            }else {
                log.error("方法执行异常！",e);
            }
        }finally {
            stopWatch.stop();
        }
    }

    /**
     * 三个入参，无返回值
     * @param t1 入参1
     * @param t2 入参2
     * @param t3 入参3
     * @param consumer 执行方法接口
     * @param onError 异常处理接口
     * @param <T1> 入参一类型
     * @param <T2> 入参二类型
     * @param <T3> 入参三类型
     */
    public static <T1,T2,T3> void doWitch(T1 t1, T2 t2, T3 t3, TriConsumer<T1,T2,T3> consumer,Consumer<Exception> onError){
        String currentStack = getCurrentStack(Thread.currentThread().getStackTrace());
        StopWatch stopWatch = WatchHelper.getStopWatch();
        try {
            stopWatch.start(currentStack);
            consumer.accept(t1,t2,t3);
        }catch (Exception e){
            if (onError != null){
                onError.accept(e);
            }else {
                log.error("方法执行异常！",e);
            }
        }finally {
            stopWatch.stop();
        }
    }

    /**
     * 无入参、有返回值
     * @param callable 待执行方法接口
     * @param onError 异常处理方法接口
     * @return V
     * @param <V> 返回值
     */
    public static <V> V doWitch(Callable<V> callable,Consumer<Exception> onError){
        String currentStack = getCurrentStack(Thread.currentThread().getStackTrace());
        StopWatch stopWatch = WatchHelper.getStopWatch();
        V v = null;
        try {
            stopWatch.start(currentStack);
            v = callable.call();
        }catch (Exception e){
            if (onError != null){
                onError.accept(e);
            }else {
                log.error("方法执行异常！",e);
            }
        }finally {
            stopWatch.stop();
        }
        return v;
    }

    /**
     * 一入参，有返回值
     * @param t 入参
     * @param function 待执行方法接口
     * @param onError 异常处理方法接口
     * @return V
     * @param <T> 入参一类型
     * @param <V> 返回值类型
     */
    public static <T,V> V doWitch(T t, Function<T,V> function,Consumer<Exception> onError){
        String currentStack = getCurrentStack(Thread.currentThread().getStackTrace());
        StopWatch stopWatch = WatchHelper.getStopWatch();
        V v = null;
        try {
            stopWatch.start(currentStack);
            v = function.apply(t);
        }catch (Exception e){
            if (onError != null){
                onError.accept(e);
            }else {
                log.error("方法执行异常！",e);
            }
        }finally {
            stopWatch.stop();
        }
        return v;
    }

    /**
     * 二入参，有返回值
     * @param t1 入参1
     * @param t2 入参2
     * @param function 待执行方法接口
     * @param onError 异常处理方法接口
     * @return V
     * @param <T1> 入参一类型
     * @param <T2> 入参二类型
     * @param <V> 返回值类型
     */
    public static <T1,T2,V> V doWitch(T1 t1, T2 t2, BiFunction<T1,T2,V> function, Consumer<Exception> onError){
        String currentStack = getCurrentStack(Thread.currentThread().getStackTrace());
        StopWatch stopWatch = WatchHelper.getStopWatch();
        V v = null;
        try {
            stopWatch.start(currentStack);
            v = function.apply(t1,t2);
        }catch (Exception e){
            if (onError != null){
                onError.accept(e);
            }else {
                log.error("方法执行异常！",e);
            }
        }finally {
            stopWatch.stop();
        }
        return v;
    }

    /**
     * 三入参，有返回值
     * @param t1 入参1
     * @param t2 入参2
     * @param t3 入参3
     * @param function 待执行方法接口
     * @param onError 异常处理方法接口
     * @return V
     * @param <T1> 入参一类型
     * @param <T2> 入参二类型
     * @param <T3> 入参三类型
     * @param <V> 返回值类型
     */
    public static <T1,T2,T3,V> V doWitch(T1 t1, T2 t2,T3 t3, TriFunction<T1,T2,T3,V> function, Consumer<Exception> onError){
        String currentStack = getCurrentStack(Thread.currentThread().getStackTrace());
        StopWatch stopWatch = WatchHelper.getStopWatch();
        V v = null;
        try {
            stopWatch.start(currentStack);
            v = function.apply(t1,t2,t3);
        }catch (Exception e){
            if (onError != null){
                onError.accept(e);
            }else {
                log.error("方法执行异常！",e);
            }
        }finally {
            stopWatch.stop();
        }
        return v;
    }


    private static String getCurrentStack(StackTraceElement[] traceElements){
        if (traceElements == null || traceElements.length ==0){
            return "";
        }
        return Arrays.stream(traceElements)
                .skip(2)
                .map(trace -> STR."\{trace.getFileName()}:\{trace.getMethodName()} line number:\{trace.getLineNumber()} -->")
                .findFirst().orElse("");
    }
}
