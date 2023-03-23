package com.Concurrent_Java.Task_006;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class TimeOutCancelTest {
     public static void main(String[] args) throws BrokenBarrierException, InterruptedException, TimeoutException {

        ImprovedTestHarness improvedTestHarness = new ImprovedTestHarness();

        System.out.println(String.format("The execution time:[%d]", improvedTestHarness.timeTasks(5, 5, ()->{
            try{
                TimeUnit.SECONDS.sleep(10);
            }catch(InterruptedException interruptedException){
                System.out.println(Thread.currentThread().getName() + " has been cancelled.");
            }
         })));
    }
}
