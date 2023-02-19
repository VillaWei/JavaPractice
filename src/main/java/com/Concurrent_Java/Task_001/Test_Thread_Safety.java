package com.Concurrent_Java.Task_001;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.Concurrent_Java.NotThreadSafe;
import com.Concurrent_Java.ThreadSafe;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

public class Test_Thread_Safety{
    @NotThreadSafe
    public static class UnsafeThread {
        private int value;

        public int getNext() {
            return value++;
        }
    }

    @ThreadSafe
     public static class SafeThread {
        private int value;

        public synchronized int getNext() {
            return value++;
        }
    }

public static void main(String[] args) throws InterruptedException {
        ExecutorService pool = Executors.newFixedThreadPool(2);
        CountDownLatch countDownLatch = new CountDownLatch(2);

        UnsafeThread unsafeSequence = new UnsafeThread();
        SafeThread safeSequence = new SafeThread();

        List<Integer> seqList1 = new ArrayList<>();
        List<Integer> seqList2 = new ArrayList<>();

        int threads = 1000;

        pool.submit(() -> {
            for (int i = 0; i < threads; i++) {
                seqList1.add(unsafeSequence.getNext());
                seqList2.add(safeSequence.getNext());
            }
            countDownLatch.countDown();
        });

        pool.submit(() -> {
            for (int i = 0; i < threads; i++) {
                seqList1.add(unsafeSequence.getNext());
                seqList2.add(safeSequence.getNext());
            }
            countDownLatch.countDown();
        });

		countDownLatch.await();
		
        pool.shutdown();

        System.out.println(String.format("The unsafe sequense value:[%d]", unsafeSequence.value));
        System.out.println(String.format("The safe sequense value:[%d]", safeSequence.value));
        
    }

} 

