package com.Concurrent_Java.Task_004;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MapSafetyPerformanceTest {  
    public static void main (String[] args) throws InterruptedException {
        final Map<Integer, Integer> sychronizedMap = Collections.synchronizedMap(new HashMap<Integer, Integer>());
        performanceTest(sychronizedMap, sychronizedMap.getClass().getName());
        
        final Map<Integer, Integer> concurrentHashMap = new ConcurrentHashMap<Integer, Integer>();  
        performanceTest(concurrentHashMap, concurrentHashMap.getClass().getName());


        final Map<Integer, Integer> improvedHashMap = new ImprovedMap<Integer, Integer>(new HashMap<Integer, Integer>());
        performanceTest(improvedHashMap, improvedHashMap.getClass().getName());
    }

     private static void performanceTest(Map<Integer, Integer> testMap, String mapName) throws InterruptedException {
         TestHarness testHarness = new TestHarness();
         TaskRunnable testTask = new TaskRunnable(testMap);

         System.out.println(String.format("The [%s]'s execution time:[%d]", mapName, testHarness.timeTasks(1, testTask)));
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
        CountDownLatch countDownLatch = new CountDownLatch(2);
        ExecutorService executor = Executors.newFixedThreadPool(2);
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


