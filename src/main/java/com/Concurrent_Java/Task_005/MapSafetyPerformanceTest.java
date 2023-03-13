package com.Concurrent_Java.Task_005;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.Concurrent_Java.Task_004.ImprovedMap;
import com.Concurrent_Java.Task_004.TestHarness;

public class MapSafetyPerformanceTest {  
    public static void main (String[] args) throws InterruptedException {
        TestHarness testHarness = new TestHarness();
        ImprovedTestHarness improvedTestHarness = new ImprovedTestHarness();

        System.out.printf("TestHarness Performance Test:\n");

        performanceTest(Collections.synchronizedMap(new HashMap<Integer, Integer>()), "sychronizedMap",testHarness);
        performanceTest(new ConcurrentHashMap<Integer, Integer>(), "concurrentHashMap",testHarness);
        performanceTest(new ImprovedMap<Integer, Integer>(new HashMap<Integer, Integer>()), "improvedHashMap",testHarness);

        System.out.printf("\nImprovedTestHarness Performance Test:\n");
                        
        performanceTest(Collections.synchronizedMap(new HashMap<Integer, Integer>()), "sychronizedMap",improvedTestHarness);
        performanceTest(new ConcurrentHashMap<Integer, Integer>(), "concurrentHashMap",improvedTestHarness);
        performanceTest(new ImprovedMap<Integer, Integer>(new HashMap<Integer, Integer>()), "improvedHashMap",improvedTestHarness);


    }

     private static void performanceTest(Map<Integer, Integer> testMap, String mapName, TestHarness harness) throws InterruptedException {
         
         TaskRunnable testTask = new TaskRunnable(testMap);

         System.out.println(String.format("The [%s]'s execution time:[%d]", mapName,  harness.timeTasks(1, testTask)));
     }
}

class TaskRunnable implements Runnable{

    private final Map<Integer, Integer> map;

    public TaskRunnable(Map<Integer, Integer> testMap){
        map = testMap;
    }

    public void run(){
        testMap(map);
    }

 private static void testMap(Map<Integer, Integer> map){
        CountDownLatch countDownLatch = new CountDownLatch(3);
        ExecutorService executor = Executors.newFixedThreadPool(3);
        executor.submit(() -> {
            for (int i = 0; i < 1000000; i++) {
                map.put(i,i);
            }
            countDownLatch.countDown();
        });
        executor.submit(() -> {
            for (int i = 0; i < 1000000; i++) {
                map.put(i,i);
            }
            countDownLatch.countDown();
        });
        executor.submit(() -> {
            for (int i = 0; i < 1000000; i++) {
                map.put(i,i);
            }
            countDownLatch.countDown();
        });
       
        try{
            countDownLatch.await();
        } catch (InterruptedException e){
            Thread.currentThread().interrupt();
        }
        executor.shutdown();
    }
}


