package org.leo.aqs;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 模块名称:ReentrantLock
 * 模块描述:TODO
 *
 * @author xiaochuang.mao
 * @date 2021/5/17 20:30
 */
public class ReentrantLockUseFootPrint {
    public static void main(String[] args) {
        //公平锁
        ReentrantLock fairLock = new ReentrantLock(true);
        /**
         *
         * 公平锁: T1 直接持有锁执行
         */
        new Thread(()-> {
            fairLock.lock();
            try {
                System.out.println("t1 获取到锁在执行");
                TimeUnit.SECONDS.sleep(1000);
            }catch (InterruptedException e){
                e.printStackTrace();
            }finally {
                fairLock.unlock();
            }
        },"T1").start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        /**
         *公平锁:  T2 加入等待队列且一直保持竞争锁
         */
        new Thread(()-> {
            fairLock.lock();
            try {
                System.out.println("t2 获取到锁在执行");
//            }catch (InterruptedException e){
//                e.printStackTrace();
            }finally {
                fairLock.unlock();
            }
        },"T2").start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(()-> {
            fairLock.lock();
            try {
                System.out.println("t3 获取到锁在执行");
            }finally {
                fairLock.unlock();
            }
        },"T3").start();


    }



}
