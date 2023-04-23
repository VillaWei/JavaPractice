package main.java.com.Concurrent_Java.Task_009;

import main.java.com.Concurrent_Java.Task_005.ImprovedTestHarness;

import java.util.concurrent.*;
import java.util.function.Consumer;
import java.util.List;

public class GroceryTest {
    private static final int size = 10000;

    public static void main(String[] args) throws InterruptedException, BrokenBarrierException{
        testGrocery(new SynchronizedGrocery(size), "SynchronizedGrocery");
        testGrocery(new ImprovedGrocery(size), "ImprovedGrocery");
        testGrocery(new ImprovedSynchronizedGrocery(size),"ImprovedSynchronizedGrocery");
        testGrocery(new CopyOnWriteGrocery(size),"CopyOnWriteGrocery");
        testGrocery(new VectorGrocery(size),"VectorGrocery");
    }

    private static void testGrocery(Grocery grocery, String name) throws InterruptedException, BrokenBarrierException{
        final int threadCount = 20;

        ImprovedTestHarness improvedTestHarness = new ImprovedTestHarness();

        System.out.println(String.format("The [%s]'s execution time:[%d]",name,improvedTestHarness.timeTasks(threadCount, ()->{
            for (int i = 0; i < size; i++) {
                grocery.addFruit(i, "fruit");
                grocery.addVegetable(i, "vegetable");
            }
        })));
    }
}


