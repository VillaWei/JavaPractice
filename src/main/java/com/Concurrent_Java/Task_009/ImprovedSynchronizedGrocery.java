package main.java.com.Concurrent_Java.Task_009;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;

public class ImprovedSynchronizedGrocery implements Grocery{
    private final List<String> fruits = Collections.synchronizedList(new ArrayList<>());
    private final List<String> vegetables = Collections.synchronizedList(new ArrayList<>());

    public ImprovedSynchronizedGrocery(int size) {
        IntStream.range(0, size).forEach(i -> {
            fruits.add("");
            vegetables.add("");
        });
    }

    public void addFruit(int index, String fruit) {
        fruits.add(index, fruit);
    }

    public void addVegetable(int index, String vegetable) {
        vegetables.add(index, vegetable);
    }

}
