package org.jggug.kobo.gircbot.core;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class TimeMonitor {

    private static final int START_DELAY = 10; // sec
    private static final int INTERVAL = 5;     // sec

    protected Executor executor;
    protected PrimaryMonitor primaryMonitor;
    protected List<TimeEventListener> listeners = new ArrayList<TimeEventListener>();

    public TimeMonitor() {
    }

    public TimeMonitor(List<TimeEventListener> listeners) {
        this.listeners.addAll(listeners);
    }

    void addTimeEventListener(TimeEventListener listener) {
        listeners.add(listener);
    }

    public void start() {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                if (primaryMonitor == null || !primaryMonitor.isPrimaryGlobally()) return;
                Date time = new Date();
                for (TimeEventListener listener : listeners) {
                    listener.invoke(time);
                }
            }
        };
        Executors.newSingleThreadScheduledExecutor().scheduleWithFixedDelay(runnable, START_DELAY, INTERVAL, TimeUnit.SECONDS);
    }
}
