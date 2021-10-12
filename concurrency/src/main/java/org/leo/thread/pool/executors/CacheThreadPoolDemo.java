package org.leo.thread.pool.executors;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 模块名称:线程池工具类
 * 模块描述:缓存线程池 newCacheThreadPool()
 * 具体实现:
 *         new ThreadPoolExecutor(0, Integer.MAX_VALUE,
 *                                       60L, TimeUnit.SECONDS,
 *                                       new SynchronousQueue<Runnable>())
 *         1.工作线程空闲60s会被回收,在提交任务会创建新线程执行任务
 *         2.使用SynchronousQueue阻塞队列不存储任务,每提交一个任务就需要一个线程来执行,
 *         而线程有不限制线程个数(Integer.MAX_VALUE),同时运行的任务数过多会导致OOM
 * @author xiaochuang.mao
 * @date 2021/7/14 17:21
 */
public class CacheThreadPoolDemo {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newCachedThreadPool();
        for (int i = 0; i < 100 ; i++) {
            final int count = i;
            executor.execute(() -> System.out.println(Thread.currentThread().getName()+" 执行 "+count));
        }
        executor.shutdown();
    }
}
