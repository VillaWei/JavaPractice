package com.Concurrent_Java.Task_006;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class TimeOutCancelTest {
     public static void main(String[] args) throws BrokenBarrierException, InterruptedException, TimeoutException {
        final int THREAD_COUNT = 5;
        final int TIMEOUT_IN_SECOND = 5;
        final int PROCESS_TIME = 10;

        ImprovedTestHarness improvedTestHarness = new ImprovedTestHarness();

        System.out.println(String.format("The execution time:[%d]", improvedTestHarness.timeTasks(THREAD_COUNT, TIMEOUT_IN_SECOND, ()->{
            try{
                TimeUnit.SECONDS.sleep(PROCESS_TIME);
            }catch(InterruptedException interruptedException){
                System.out.println(Thread.currentThread().getName() + " has been cancelled.");
            }
         })));
    }
}
