package com.Concurrent_Java.Task_003;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import com.Concurrent_Java.ThreadSafe;

public class SafeAndUnsafeHashMapTest {
    public static void main(String[] args) throws InterruptedException {

        ExecutorService pool = Executors.newFixedThreadPool(2);
        CountDownLatch countDownLatch = new CountDownLatch(2);

        final Map<Integer, Integer> unSafeHashMap = new HashMap<>();
        final ImprovedHashMap<Integer, Integer> improvedHashMap = new ImprovedHashMap<Integer, Integer>();

        int threads = 1000;

        pool.submit(() -> {
            for (int i = 0; i < threads; i++) {
                unSafeHashMap.put(i, i);
                improvedHashMap.put(i, i);
            }
            countDownLatch.countDown();
        });

        pool.submit(() -> {
          for (int i = 0; i < threads; i++) {
                unSafeHashMap.put(i, i);
                improvedHashMap.put(i, i);
            }
            countDownLatch.countDown();
        });

		countDownLatch.await();

        pool.shutdown();

        System.out.println(String.format("The current HashMap's size should be [%d]", threads));
        System.out.println(String.format("The unsafe HashMap's size:[%d]", unSafeHashMap.size()));
        System.out.println(String.format("The safe HashMap's size:[%d]", improvedHashMap.size()));
    }
}

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


