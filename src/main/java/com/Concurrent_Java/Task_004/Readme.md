Java Concurrency in Practice - Task 004

任务描述：读完 Chapter 5 (page 118)测试以下3个Map 在并发场景的性能:
1. Collections.synchronizedMap
2. ConcurrentHashMap
3. 你在 Task 003 中实现的线程安全的 

Map测试方式二选一：
1. 使用 Listing 5.11 提供的TestHarness 工具，进行简单(非严格)的度量；
2. 或者采用JMH (可以参考https://confluence.kingland-apps.com/display/~biyan@ksd.kingland.cc/2022/03/25/Code+Performance+and+Microbenchmarking)， 进行相对严格的度量；

Deadline：3月6日中午12点最先完成任务且无明显缺陷的前3名同学将各获得一枚免死金牌 🏅️

指出前三名明显缺陷的同学可以抢得🏅️使用相对严格模式证实自己的实现比 ConcurrentHashMap性能更好的，可以获得🏅️一枚