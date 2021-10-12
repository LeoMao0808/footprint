package org.leo.thread.pool.executors;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 模块名称:线程池工具类
 * 模块描述:单线程池 newSingleThreadExecutor()
 *         特点:可保证顺序执行各个任务
 * 具体实现:
 *         new ThreadPoolExecutor(1, 1,
 *                                  0L, TimeUnit.MILLISECONDS,
 *                                  new LinkedBlockingQueue<Runnable>())
 * @author xiaochuang.mao
 * @date 2021/7/14 17:21
 */
public class SingleThreadExecutorDemo {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        for (int i = 0; i < 100 ; i++) {
            final int count = i;
            executor.execute(() -> System.out.println(Thread.currentThread().getName()+" 执行 "+count));
        }
        executor.shutdown();
    }
}
