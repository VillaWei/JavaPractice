package com.Concurrent_Java.Task_007;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Logger;

public class TimingThreadPool extends ThreadPoolExecutor {
    private static final Logger logger = Logger.getLogger("TimingThreadPool");
    private static final AtomicLong taskNum = new AtomicLong();
    private static final AtomicLong totalCost = new AtomicLong();
    private static final ThreadLocal<Long> startTime = new ThreadLocal<>();
    private boolean shouldStartAllCoreThread;

    private static final ThreadFactory threadFactory = new ThreadFactory() {
        private int counter = 0;

        @Override
        public Thread newThread(Runnable runnable) {
            return new Thread(runnable, "Timing Thread Pool " + (counter++));
        }
    };

    public TimingThreadPool(int corePoolSize, boolean shouldStartAllCoreThread) {

        super(corePoolSize, corePoolSize, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue(), threadFactory,
                new ThreadPoolExecutor.AbortPolicy());
        this.shouldStartAllCoreThread = shouldStartAllCoreThread;
        if (shouldStartAllCoreThread)
            super.prestartAllCoreThreads();
    }

    @Override
    protected void beforeExecute(Thread t, Runnable r) {
        super.beforeExecute(t, r);
        startTime.set(System.nanoTime());
        logger.fine(String.format("Thread %s: start %s", t, r));
    }

    @Override
    protected void afterExecute(Runnable r, Throwable t) {
        try {
            long end = System.nanoTime();
            long timeTask = end - startTime.get();
            totalCost.addAndGet(timeTask);
            taskNum.incrementAndGet();
            logger.fine(String.format("Thread %s end %s, time= %dns",
                    Thread.currentThread().getName(), r, timeTask));
        } finally {
            super.afterExecute(r, t);
        }
    }

    @Override
    protected void terminated() {
        try {
            logger.info(String.format("Terminated:isPreStartCoreThread %s avg %dns", shouldStartAllCoreThread, totalCost.get() / taskNum.get()));
        } finally {
            super.terminated();
        }
    }
}
