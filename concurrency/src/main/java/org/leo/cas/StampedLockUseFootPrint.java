package org.leo.cas;

import java.util.concurrent.locks.StampedLock;

/**
 * 模块名称:StampedLock
 * 模块描述:StampedLock支持三种模式，分别是：写锁、悲观读锁和乐观读。
 * StampedLock的性能之所以比ReadWriteLock还要好，其关键是StampedLock支持乐观读的方式。
 * ReadWriteLock支持多个线程同时读，但是当多个线程同时读的时候，所有的写操作会被阻塞；
 * StampedLock提供的乐观读，是允许一个线程获取写锁的，也就是说不是所有的写操作都被阻塞。(乐观读是无锁的)
 * 注意: 如果线程阻塞在StampedLock的readLock()或者writeLock()上时，此时调用该阻塞线程的interrupt()方法，会导致CPU飙升
 * 使用StampedLock一定不要调用中断操作，如果需要支持中断功能，一定使用可中断的悲观读锁readLockInterruptibly()和写锁writeLockInterruptibly()
 * @author xiaochuang.mao
 * @date 2021/5/13 20:17
 */
public class StampedLockUseFootPrint {

    public static void main(String[] args) {
        Point.distanceFromOrigin();
    }

}
//例子
class Point{
    private static int x = 2,y = 3;
    final static  StampedLock sl = new StampedLock();
    //计算到原点的距离
    static double distanceFromOrigin(){
        //乐观读
        long stamp = sl.tryOptimisticRead();
        //赋值局部变量
        //这个过程会出现数据被修改
        int curX = x;int curY = y;
        //判断执行读操作的过程中是否存在写操作
        if(!sl.validate(stamp)){
            //升级为悲观读锁
            stamp = sl.readLock();
            try{
                curX = x;
                curY = y;
            }finally {
                sl.unlockRead(stamp);
            }
        }
        return Math.sqrt(curX * curX + curY * curY);
    }

    public static void main(String[] args) {
        System.out.println(distanceFromOrigin());
    }
}
