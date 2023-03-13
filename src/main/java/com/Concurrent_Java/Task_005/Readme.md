任务描述：

* 读完 Chapter 6 (page 142)
* 改进 Listing 5.11 提供的 TestHarness 工具，新类名 ImprovedTestHarness，要求
* 使用 CyclicBarrier 而不是 CountDownLatch;
* 不得使用 unbounded thread;
* 分别使用 ImprovedTestHarness 和 Listing 5.11 提供的TestHarness 对 Task 004进行简单(非严格)的度量，比较结果；


Deadline：3月15日中午12点
最先完成任务且无明显缺陷的前3名同学将各获得一枚免死金牌 🏅️
指出前三名明显缺陷的同学可以抢得🏅


TestHarness Performance Test:
* The [java.util.Collections$SynchronizedMap]'s execution time:[1051742217]
* The [java.util.concurrent.ConcurrentHashMap]'s execution time:[765404498]
* The [com.Concurrent_Java.Task_004.ImprovedMap]'s execution time:[1034124902]

TestHarness Peformance Test Result: ConcurrentHashMap is the winner.


ImprovedTestHarness Performance Test:
* The [java.util.Collections$SynchronizedMap]'s execution time:[3665]
* The [java.util.concurrent.ConcurrentHashMap]'s execution time:[3025]
* The [com.Concurrent_Java.Task_004.ImprovedMap]'s execution time:[2911]

ImprovedTestHarness Performance Test: ImprovedMap is the winner.
