package org.leo.aqs;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

/**
 * 模块名称:CyclicBarrier
 * 模块描述:多个线程步调一致
 * 核心方法:
 * 1. new CyclicBarrier(int parties, Runnable barrierAction): 初始化一个计数器,以及回调方法(计数器为0)
 * 2. cyclicBarrier.await() : 计数器值-1
 * 4. cyclicBarrier.await(long timeout, TimeUnit unit) : 超时计数器值-1
 *区别:
 * CountDownLatch主要用来解决一个线程等待多个线程的场景；
 * 而CyclicBarrier是一组线程之间互相等待.
 * 除此之外CountDownLatch的计数器是不能循环利用的，也就是说一旦计数器减到0，
 * 再有线程调用await()，该线程会直接通过。
 * CyclicBarrier的计数器是可以循环利用的，而且具备自动重置的功能，
 * 一旦计数器减到0会自动重置到你设置的初始值。除此之外，CyclicBarrier还可以设置回调函数.
 * @author xiaochuang.mao
 * @date 2021/5/14 19:53
 */
public class CyclicBarrierUseFootPrint {
    /**订单队列*/
   static Vector<List<String>> pos = new Vector<List<String> > ();
    /**派送单队列*/
    static Vector<List<String>> dos = new Vector<List<String> > ();
    /**执行回调的线程池*/
    static Executor executor = Executors.newFixedThreadPool(2);

    public static void main(String[] args) throws InterruptedException {

        final CyclicBarrier barrier =
                new CyclicBarrier(2, () -> {
                    executor.execute(CyclicBarrierUseFootPrint::check);
                });

            executor.execute(()->{
                while(checkIsHasDiffOrders()){
                    try {
                        pos.add(getOrders());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }finally {
                        try {
                            barrier.await();
                        } catch (InterruptedException | BrokenBarrierException e) {
                            e.printStackTrace();
                        }
                    }
                }
            });

        executor.execute(()->{
            while(checkIsHasDiffOrders()){
                try {
                    dos.add(getOrders2());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    try {
                        barrier.await();
                    } catch (InterruptedException | BrokenBarrierException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

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

    public static void check(){
        List<String> p = pos.remove(0);
        List<String> d = dos.remove(0);
        boolean diff = checkDiff(p, d);
        save(diff);
    }

    public static boolean checkDiff(List<String> o1,List<String> o2){
        return o1.size() == o2.size();
    }

    public static void save(boolean result){
        System.out.println("保存成功");
    }

}

