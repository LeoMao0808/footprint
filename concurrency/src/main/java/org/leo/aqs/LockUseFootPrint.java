package org.leo.aqs;

import jdk.nashorn.internal.objects.annotations.Where;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 模块名称:LockAndCondition
 * Java SDK并发包通过Lock和Condition两个接口来实现管程，其中Lock用于解决互斥问题，Condition用于解决同步问题
 *互斥锁的三种方案：
 * Lock接口中的三个方法
 *  1.支持响应中断 ；lockInterruptibly() // 支持中断的API
 *  2.支持超时 ；boolean tryLock(long time, TimeUnit unit) //支持超时
 *  3.非阻塞的获取锁 ; boolean tryLock() //支持非阻塞获取锁
 * @author xiaochuang.mao
 * @date 2021/5/12 16:38
 */
public class LockUseFootPrint {
   private final Lock lock = new ReentrantLock();
    int count;
   public void addOne(){
       lock.lock();
       try{
           count++;
       }finally {
           lock.unlock();
       }
   }

   public void addOneInterruptible() throws InterruptedException {
       lock.lockInterruptibly();
       try {
           while(true) {
               System.out.println("runing");
           }
       } finally {
           lock.unlock();
       }
   }

    public static void main(String[] args) throws InterruptedException {
       final Lock lock = new ReentrantLock();
        new Thread(()->{
            lock.lock();
            try {
                System.out.println("持有锁开始sleep");
                TimeUnit.SECONDS.sleep(10);
                System.out.println("持有锁结束sleep");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                lock.unlock();
                System.out.println("thread1释放锁");
            }
        }).start();

        new Thread(()->{
//            lock.tryLock();
//            try {
//                System.out.println("尝试获取锁");
//                lock.tryLock(5,TimeUnit.SECONDS);
//                System.out.println("持有锁了没");
//                System.out.println("1221");
//            } catch (InterruptedException e) {
//                e.printStackTrace();
            System.out.println("尝试获取锁");
            lock.lock();
            try {
                System.out.println("持有锁了没");
                System.out.println("1221");
            }catch (Exception e){
                e.printStackTrace();
            }finally {
                lock.unlock();
            }
        }).start();
        Thread.currentThread().join();
        System.out.println("main");
    }
}
