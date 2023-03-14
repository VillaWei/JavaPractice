package com.Concurrent_Java.Task_005;
 
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.Concurrent_Java.Task_004.TestHarness;
 
public class ImprovedTestHarness extends TestHarness{
    private final static int INVALID_RETURN_VALUE = -1;
    public long timeTasks(int nThreads, final Runnable task) throws InterruptedException{
        // Main thread is 1; nThreads(sub threads) is 10;
        final CyclicBarrier startGate = new CyclicBarrier(nThreads + 1);
        final CyclicBarrier endGate = new CyclicBarrier(nThreads + 1);
        final ExecutorService executorService = Executors.newFixedThreadPool(nThreads);
 
        for(int i = 0; i < nThreads; i++) {
            executorService.execute(new Thread(() -> {
                try {
                    startGate.await();
                    try {
                        task.run();
                    } finally {
                        endGate.await();
                    }
                } catch (BrokenBarrierException | InterruptedException ignored) {
                    Thread.currentThread().interrupt();
                }
            }));
 
        }
        try {
            startGate.await();
            long start = System.nanoTime();
            endGate.await();
            long end = System.nanoTime();
            return end-start;
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
            return INVALID_RETURN_VALUE;
        } finally {
            executorService.shutdown();
        }
    }
}