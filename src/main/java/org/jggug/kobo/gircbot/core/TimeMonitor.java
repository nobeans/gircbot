package org.jggug.kobo.gircbot.core;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class TimeMonitor {

    private static final long START_DELAY = 0L; // sec
    private static final long INTERVAL = 1L; // sec

    private boolean started = false;
    private final List<TimeEventListener> listeners = new ArrayList<TimeEventListener>();
    private final ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
    private PrimaryMonitor primaryMonitor;

    public void setPrimaryMonitor(PrimaryMonitor primaryMonitor) {
        this.primaryMonitor = primaryMonitor;
    }

    public void addTimeEventListener(TimeEventListener listener) {
        listeners.add(listener);
    }

    public void start() {
        if (started) {
            throw new IllegalStateException("Time monitor is already started.");
        }
        Runnable runnable = createRunnableWithCopiedListeners();
        executor.scheduleAtFixedRate(runnable, START_DELAY, INTERVAL, TimeUnit.SECONDS);
        started = true;
    }

    private Runnable createRunnableWithCopiedListeners() {
        final List<TimeEventListener> localListeners = new ArrayList<TimeEventListener>(listeners);
        return new Runnable() {
            @Override
            public void run() {
                if (primaryMonitor == null || !primaryMonitor.isPrimaryGlobally()) return;
                Date time = new Date();
                for (TimeEventListener listener : localListeners) {
                    listener.invoke(time);
                }
            }
        };
    }
}
