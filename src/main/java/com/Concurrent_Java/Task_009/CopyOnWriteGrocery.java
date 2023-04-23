package main.java.com.Concurrent_Java.Task_009;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.IntStream;

public class CopyOnWriteGrocery implements Grocery {
    private final List<String> fruits = new CopyOnWriteArrayList<>();
    private final List<String> vegetables = new CopyOnWriteArrayList<>();

    public CopyOnWriteGrocery(int size) {
        IntStream.range(0, size).forEach(index -> {
            fruits.add(null);
            vegetables.add(null);
        });
    }

    public void addFruit(int index, String fruit) {
        fruits.add(index, fruit);
    }

    public void addVegetable(int index, String vegetable) {
        vegetables.add(index, vegetable);
    }
}

