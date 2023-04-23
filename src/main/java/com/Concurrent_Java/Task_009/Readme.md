### 任务描述：

* 读完 Chapter 11(page 261)
* 代码实验
>> + 阅读下面的代码
>> + 写一段测试代码，测试其在并发环境下的性能 
>> + 改进这段代码的性能，并和基准性能比较

```
public interface Grocery {
    void addFruit(int index, String fruit);
    void addVegetable(int index, String vegetable);
}
```

```
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
```

#### Deadline：4月25日中午12点

---
## 测试结果：
#### 5 threads
```
The [SynchronizedGrocery]'s execution time:         [993815488]
The [ImprovedGrocery]'s execution time:             [674611079]
The [ImprovedSynchronizedGrocery]'s execution time: [694993892]
The [CopyOnWriteGrocery]'s execution time:          [2383687017]
The [VectorGrocery]'s execution time:               [817379671]
```

#### 10 threads
```
The [SynchronizedGrocery]'s execution time:         [3235638096]
The [ImprovedGrocery]'s execution time:             [2035806825]
The [ImprovedSynchronizedGrocery]'s execution time: [2015212975]
The [CopyOnWriteGrocery]'s execution time:          [9579336907]
The [VectorGrocery]'s execution time:               [2123390156]
```
#### 20 threads
```
The [SynchronizedGrocery]'s execution time:         [10061502165]
The [ImprovedGrocery]'s execution time:             [8430517098]
The [ImprovedSynchronizedGrocery]'s execution time: [8715607706]
The [CopyOnWriteGrocery]'s execution time:          [98388863845]
The [VectorGrocery]'s execution time:               [8168117956]
```








