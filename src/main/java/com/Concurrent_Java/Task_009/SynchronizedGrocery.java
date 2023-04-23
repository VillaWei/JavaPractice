package main.java.com.Concurrent_Java.Task_009;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class SynchronizedGrocery implements Grocery {
    private final List<String> fruits = new ArrayList<>();
    private final List<String> vegetables = new ArrayList<>();

    SynchronizedGrocery(int size) {
        IntStream.range(0, size).forEach(index -> {
            fruits.add(null);
            vegetables.add(null);
        });
    }

    public synchronized void addFruit(int index, String fruit) {
        fruits.add(index, fruit);
    }

    public synchronized void addVegetable(int index, String vegetable) {
        vegetables.add(index, vegetable);
    }
}

