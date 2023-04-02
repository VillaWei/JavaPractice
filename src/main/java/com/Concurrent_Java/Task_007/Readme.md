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


