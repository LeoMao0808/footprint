package org.leo.threadlocal;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 模块名称:ThreadLocal
 * 模块描述:ThreadLocal的应用
 *
 * @author xiaochuang.mao
 * @date 2021/7/30 9:34
 */
public class ThreadLocalFootPrint {
    private static final ThreadLocal<Integer> LOCAL = ThreadLocal.withInitial(() -> 0);
    public static void main(String[] args) {
        Runnable runTask = new Runnable() {
            @Override
            public void run() {
                Integer count = LOCAL.get();
                for (int i = 0; i < 10; i++) {
                    count++;
                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                LOCAL.set(count);
                LOCAL.remove();
                System.out.println(Thread.currentThread().getName() + ":" + count);
            }
        };

        ExecutorService exec = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 10; i++) {
            exec.execute(runTask);
        }
            exec.shutdown();

    }
}
