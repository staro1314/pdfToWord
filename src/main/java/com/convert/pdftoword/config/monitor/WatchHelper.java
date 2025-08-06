package com.convert.pdftoword.config.monitor;

import com.convert.pdftoword.config.monitor.watch.WatchBoost;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StopWatch;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Slf4j
public class WatchHelper {
    public static final Map<String, StopWatch> WATCH_MAP= Collections.synchronizedMap(new HashMap<>());

    public static StopWatch createStopWatch() {
        String id = String.valueOf(Thread.currentThread().threadId());
        StopWatch stopWatch = new WatchBoost();
        WATCH_MAP.put(id, stopWatch);
        return stopWatch;
    }

    public static StopWatch getStopWatch() {
        String id = String.valueOf(Thread.currentThread().threadId());
        StopWatch stopWatch = WATCH_MAP.get(id);
        return stopWatch == null ? new WatchBoost(id) : stopWatch;
    }

    public static void removeStopWatch() {
        String id = String.valueOf(Thread.currentThread().threadId());
        removeStopWatch(id);
    }

    public static void removeStopWatch(String id) {
        WATCH_MAP.remove(id);
    }

    public static void prettyPrint(){
        String id = String.valueOf(Thread.currentThread().threadId());
        StopWatch stopWatch = WATCH_MAP.get(id);
        if (stopWatch != null){
            log.info(stopWatch.prettyPrint(TimeUnit.MILLISECONDS));
            WATCH_MAP.remove(id);
        }
    }

}
