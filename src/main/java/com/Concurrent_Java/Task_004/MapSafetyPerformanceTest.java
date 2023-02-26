package com.Concurrent_Java.Task_004;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import com.Concurrent_Java.ThreadSafe;

@ThreadSafe
class ImprovedHashMap<K, V> {
    private final HashMap<K, V> map;
 
    public ImprovedHashMap() {
        map = new HashMap<>();
    }
 
    public void put(K key, V value) {
        synchronized (map) {
            map.put(key, value);
        }
    }
 
    public int size() {
        synchronized (map) {
            return map.size();
        }
    }
}
public class MapSafetyPerformanceTest {  
    public static void main (String[] args) throws InterruptedException {
        final Map<Integer, Integer> sychronizedMap = new Collections.synchronizedMap(new HashMap<Integer, Integer>());
        performanceTest(sychronizedMap);
        
        final Map<Integer, Integer> concurrentHashMap = new ConcurrentHashMap<Integer, Integer>();  
        performanceTest(concurrentHashMap);

        final Map<Integer, Integer> improvedHashMap = new ImprovedHashMap<Integer, Integer>();
        performanceTest(improvedHashMap);
    }

     private static void performanceTest(Map<Integer, Integer> testMap) throws InterruptedException {
         TestHarness testHarness = new TestHarness();
         TaskRunnable testTask = new TaskRunnable(testMap);

         System.out.println(String.format("The execute time:[%d]", testHarness.timeTasks(1, testTask)));
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
            for (int i = 0; i < 100000; i++) {
                map.put(i,i);
            }
            countDownLatch.countDown();
        });
        executor.submit(() -> {
            for (int i = 0; i < 100000; i++) {
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


