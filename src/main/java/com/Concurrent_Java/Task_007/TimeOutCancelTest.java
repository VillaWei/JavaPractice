package com.Concurrent_Java.Task_007;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class TimeOutCancelTest {
     public static void main(String[] args) throws BrokenBarrierException, InterruptedException, TimeoutException {
        final int threadCount = 5;
        final int timeoutInSecond = 5;
        final int processTime = 10;

        ImprovedTestHarness improvedTestHarness = new ImprovedTestHarness();

        System.out.println(String.format("The execution time:[%d]", improvedTestHarness.timeTasks(threadCount, timeoutInSecond, ()->{
            try{
                TimeUnit.SECONDS.sleep(processTime);
            }catch(InterruptedException interruptedException){
                System.out.println(Thread.currentThread().getName() + " has been cancelled.");
            }
         }, true)));

         System.out.println(String.format("The execution time:[%d]", improvedTestHarness.timeTasks(threadCount, timeoutInSecond, ()->{
            try{
                TimeUnit.SECONDS.sleep(processTime);
            }catch(InterruptedException interruptedException){
                System.out.println(Thread.currentThread().getName() + " has been cancelled.");
            }
         }, false)));
    }
}
