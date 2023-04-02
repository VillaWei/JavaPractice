package com.Concurrent_Java.Task_007;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicLong;

public class ImprovedTestHarness {
    public long timeTasks(int taskCount, int timeoutInSeconds, final Runnable task, boolean shouldStartAllCoreThread) throws InterruptedException, BrokenBarrierException {
        AtomicLong start = new AtomicLong();
        final CyclicBarrier startGate = new CyclicBarrier(taskCount + 1);
        final CyclicBarrier endGate = new CyclicBarrier(taskCount + 1);

        ExecutorService executorService = new TimingThreadPool(taskCount,shouldStartAllCoreThread);

        try {
            for (int i = 0; i < taskCount; i++) {
                executorService.submit(() -> {
                    try {
                        startGate.await();
                        System.out.println(Thread.currentThread().getName() + "start time:" + System.currentTimeMillis());
                        ExecutorService singleExecutor = Executors.newSingleThreadExecutor();
                        Future<?> future = singleExecutor.submit(task);
                        try {
                            future.get(timeoutInSeconds, TimeUnit.SECONDS);
                        }catch (ExecutionException executionException){
                            executionException.printStackTrace();
                        }catch (TimeoutException timeoutException){
                            future.cancel(true);
                            System.out.println("Task is canceled due to timeout. Thread: " + Thread.currentThread().getName());
                        }finally{
                            singleExecutor.shutdown();
                            endGate.await(); 
                        }
                    } catch (InterruptedException | BrokenBarrierException ignore) {
                        Thread.currentThread().interrupt();
                    }
                });
            }

            startGate.await();
            endGate.await();

            return System.nanoTime() - start.get();
        } finally {
            executorService.shutdown();
        }
    }
}
