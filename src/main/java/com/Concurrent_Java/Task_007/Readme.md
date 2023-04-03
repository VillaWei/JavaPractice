### 任务描述：

* 读完 Chapter 8(page 200)
* 改进  Code Listing 8.9 的 TimingThreadPool，要求：
* 构造器提供至少两个参数：
* `corePoolSize` – the number of threads to keep in the pool, even if they are idle
* `shouldStartAllCoreThreads` - 是否在线程池创建时(而不是在 task 到来时才)开启所有的核心线程;
* 在Task 006中，使用新的 TimingThreadPool 来作为executor；
* 比较 `shouldStartAllCoreThreads` 分别为 `true`和 `false` 时，测试结果的差异

#### Deadline：4月3日中午12点

---

### Test Result:

**INFO: Terminated:isPreStartCoreThread true avg 5101214387ns**

```java
pool-1-thread-3start time:1680486321948

pool-1-thread-5start time:1680486321985

pool-1-thread-1start time:1680486321954

pool-1-thread-4start time:1680486321955

pool-1-thread-2start time:1680486321948

pool-2-thread-1 has been cancelled.

Task is canceled due to timeout. Thread: pool-1-thread-3

Task is canceled due to timeout. Thread: pool-1-thread-2
pool-6-thread-1 has been cancelled.

Task is canceled due to timeout. Thread: pool-1-thread-1
pool-5-thread-1 has been cancelled.

Task is canceled due to timeout. Thread: pool-1-thread-5
pool-4-thread-1 has been cancelled.

Task is canceled due to timeout. Thread: pool-1-thread-4
pool-3-thread-1 has been cancelled.

The execution time:[1588399928460]

pool-7-thread-5start time:1680486327048

pool-7-thread-1start time:1680486327048

pool-7-thread-2start time:1680486327048

pool-7-thread-4start time:1680486327048

pool-7-thread-3start time:1680486327050

Apr 03, 2023 1:45:27 AM main.java.com.Concurrent_Java.Task_007.TimingThreadPool terminated
```

---
**INFO: Terminated:isPreStartCoreThread false avg 5054545795ns**


```java
Task is canceled due to timeout. Thread: pool-7-thread-1

Task is canceled due to timeout. Thread: pool-7-thread-4

pool-8-thread-1 has been cancelled.

pool-11-thread-1 has been cancelled.

Task is canceled due to timeout. Thread: pool-7-thread-3

Task is canceled due to timeout. Thread: pool-7-thread-2

pool-12-thread-1 has been cancelled.

Task is canceled due to timeout. Thread: pool-7-thread-5

pool-9-thread-1 has been cancelled.

pool-10-thread-1 has been cancelled.

The execution time:[1593409450949]

Apr 03, 2023 1:45:32 AM main.java.com.Concurrent_Java.Task_007.TimingThreadPool terminated
```



