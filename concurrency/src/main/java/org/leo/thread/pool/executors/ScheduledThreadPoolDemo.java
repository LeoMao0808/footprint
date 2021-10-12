package org.leo.thread.pool.executors;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * 模块名称:线程池工具类
 * 模块描述:支持定时以及周期性执行任务的线程池 newScheduleThreadPool(int corePoolSize)
 *  具体实现:
 *          return new ScheduledThreadPoolExecutor(corePoolSize);
 *                  super(corePoolSize, Integer.MAX_VALUE,
 *                          0, NANOSECONDS,
 *                          new DelayedWorkQueue());
 *
 * @author xiaochuang.mao
 * @date 2021/7/14 19:15
 */
public class ScheduledThreadPoolDemo {
    public static void main(String[] args) {
        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(3);
        //一秒后执行
        executorService.schedule(()->System.out.println(Thread.currentThread().getName()+" 执行 "),1, TimeUnit.SECONDS);
        //两秒后执行,后面每隔一秒执行一次
        executorService.scheduleAtFixedRate(()->System.out.println(Thread.currentThread().getName()+" 执行1 "),2,1,TimeUnit.SECONDS);
        executorService.shutdown();
    }
}
