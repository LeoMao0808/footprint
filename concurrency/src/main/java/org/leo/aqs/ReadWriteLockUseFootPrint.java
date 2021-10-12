package org.leo.aqs;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 模块名称:ReadWriteLock: 适用场景是缓存  读多写少
 * 模块描述:读写锁三个基本原则：
 * 1.允许多个线程同时读共享变量；
 * 2.只允许一个线程写共享变量；
 * 3.如果一个写线程正在执行写操作，此时禁止读线程读共享变量
 * 支持读锁(lock.readLock())和写锁(lock.writeLock()) 也支持公平模式和非公平模式new ReentrantReadWriteLock(true)
 * tryLock(): 非阻塞获取锁
 * tryLock(long time,TimeUnit unit); 超时
 * lockInterruptibly(); 响应中断
 * @author xiaochuang.mao
 * @date 2021/5/12 16:35
 */
public class ReadWriteLockUseFootPrint {
     final Map<String,Object> map = new HashMap<String,Object>();
     final ReadWriteLock lock = new ReentrantReadWriteLock();
    /**读锁*/
    Lock readLock = this.lock.readLock();
    /**写锁*/
    Lock writeLock = this.lock.writeLock();
     public Object get(String key){
        readLock.lock();
        try {
            return map.get(key);
        }finally {
            readLock.unlock();
        }
     }

     public void put(String key,String value){
         writeLock.lock();
         try{
             map.put(key,value);
         }finally {
             writeLock.unlock();
         }
     }
}
