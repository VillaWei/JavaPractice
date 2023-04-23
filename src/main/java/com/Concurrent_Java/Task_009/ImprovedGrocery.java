package main.java.com.Concurrent_Java.Task_009;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

@SuppressWarnings("MismatchedQueryAndUpdateOfCollection")
public class ImprovedGrocery implements Grocery {
    private final List<String> fruits = new ArrayList<>();
    private final List<String> vegetables = new ArrayList<>();

    public ImprovedGrocery(int size) {
        IntStream.range(0, size).forEach(index -> {
            fruits.add(null);
            vegetables.add(null);
        });
    }


    public void addFruit(int index, String fruit) {
        synchronized (fruits) {
            fruits.add(index, fruit);
        }
    }

    public void addVegetable(int index, String vegetable) {
        synchronized (vegetables) {
            vegetables.add(index, vegetable);
        }
    }
}

