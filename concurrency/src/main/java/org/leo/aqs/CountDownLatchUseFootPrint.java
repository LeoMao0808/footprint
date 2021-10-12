package org.leo.aqs;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

/**
 * 模块名称:CountDownLatch
 * 模块描述:多个线程步调一致
 * 核心方法:
 * 1. new CountDownLatch(int count): 初始化一个计数器
 * 2. countDownLatch.countDown(): 计数器值 -1
 * 3. countDownLatch.await() : 阻塞线程直到计数器值为0
 * 4. countDownLatch.await(long timeout, TimeUnit unit) : 阻塞线程直到超时
 * @author xiaochuang.mao
 * @date 2021/5/14 19:53
 */
public class CountDownLatchUseFootPrint {
    static Executor exe = Executors.newFixedThreadPool(2);

    public static void main(String[] args) throws InterruptedException {
        while (checkIsHasDiffOrders()) {
            CountDownLatch countDownLatch = new CountDownLatch(2);
            AtomicReference<List<String>> o1 = null;
            AtomicReference<List<String>> o2 = null;
            exe.execute(() -> {
                try {
                   o1.set(getOrders());
                    System.out.println("t1 end");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    countDownLatch.countDown();
                }
            });
            exe.execute(() -> {
                try {
                    o2.set(getOrders2());
                    System.out.println("t1 end");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    countDownLatch.countDown();
                }
            });
            countDownLatch.await();
            boolean diff = checkDiff(o1.get(), o2.get());
            save(diff);
        }
    }



    public static boolean checkIsHasDiffOrders(){
        return true;
    }

    public static List<String> getOrders() throws InterruptedException {
        TimeUnit.SECONDS.sleep(20);
        return new ArrayList<>();
    }

    public static List<String> getOrders2() throws InterruptedException {
        TimeUnit.SECONDS.sleep(20);
        return new ArrayList<>();
    }

    public static boolean checkDiff(List<String> o1,List<String> o2){
        return o1.size() == o2.size();
    }

    public static void save(boolean result){
        System.out.println("保存成功");
    }

}

