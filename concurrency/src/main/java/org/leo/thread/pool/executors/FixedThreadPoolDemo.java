package org.leo.thread.pool.executors;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 模块名称:线程池工具类
 * 模块描述:newFixedThreadPool()
 * 创建一个固定大小的线程池
 * 具体实现:
 * new ThreadPoolExecutor(nThreads, nThreads,
 *                          0L, TimeUnit.MILLISECONDS,
 *                          new LinkedBlockingQueue<Runnable>());
 * 缺点: 线程池空闲,工作线程不会回收,占用资源
 *      使用LinkedBlockingQueue阻塞无界队列,任务挤压会导致OOM
 *
 * @author xiaochuang.mao
 * @date 2021/7/14 18:51
 */
public class FixedThreadPoolDemo {

    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(3);
        for (int i = 0; i < 100 ; i++) {
            final int count = i;
            executor.execute(() -> System.out.println(Thread.currentThread().getName()+" 执行 "+count));
        }
        executor.shutdown();
    }
}
