package com.convert.pdftoword.config.monitor.watch;

import org.springframework.util.StopWatch;

public class WatchBoost extends StopWatch {

    public WatchBoost() {
    }

    public WatchBoost(String id) {
        super(id);
    }

    public void start(String taskName){
       if (this.isRunning()){
           this.stop();
       }
       super.start(taskName);
    }

    public void stop() throws IllegalStateException {
        if (!this.isRunning()){
            this.start();
        }
        super.stop();
    }
}
