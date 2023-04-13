package com.Concurrent_Java.Task_008;

import java.util.concurrent.*;

public class DeadLockTest {

    public static void main(String[] args) throws InterruptedException {
        Object lock1 = new Object();
        Object lock2 = new Object();

        ExecutorService executor = Executors.newFixedThreadPool(2);
        executor.submit(() -> {
            try{
                synchronized (lock1){
                    TimeUnit.SECONDS.sleep(5);
                    System.out.println(Thread.currentThread().getName() + " get the lock " + lock1);
                    synchronized (lock2){
                        System.out.println(Thread.currentThread().getName() + "get the lock " + lock2);
                    }
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        executor.submit(() -> {
            try{
                synchronized (lock2){
                    TimeUnit.SECONDS.sleep(5);
                    System.out.println(Thread.currentThread().getName() + " get the lock " + lock2);
                    synchronized (lock1){
                        System.out.println(Thread.currentThread().getName() + " get the lock " + lock1);
                    }
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });
    }
}
