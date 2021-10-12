package org.leo.sync;

/**
 * 模块名称:Synchronized
 * synchronized关键字
 * synchronized关键字锁定的是对象不是代码块,demo中锁的是object对象的实例
 * 锁定的对象有两种:1.类的实例 2.类对象(类锁)
 * synchronized关键字不一定能实现线程安全，具体还要看锁定的对象是否唯一
 * @author xiaochuang.mao
 * @date 2021/5/11 20:27
 */
public class SynchronizedUseFootPrint {
    /**
     * 1.锁定的是object o的实例对象
     * @return void
     */
    Object o = new Object();
     public void syncLockObject(){
         synchronized (o){
             System.out.println("锁定对象");
         }
     }

     /**ObjectMonitor
      *2.锁定当前类的实例对象 相当于synchronized(this){}
      * @return void
      */
    public synchronized void syncLockObject1(){
        System.out.println("锁定对象1");
    }
    public void syncLockObject2(){
        synchronized (this) {
            System.out.println("锁定对象2");
        }
    }

    /**
     * 3.synchronized 锁定的是当前对象的.class文件(SynchronizedFootPrint.class)
     * 静态方法中synchronize锁定代码块，锁定的对象不能是类的实例，只能是类的.class文件
     * @return void
     */
    public synchronized static void  syncLockClass(){
        System.out.println("锁定当前对象的class");
        syncLockClass1();//sync支持重入
    }
    public static void syncLockClass1(){
//        synchronized (this){}  unused
        synchronized (SynchronizedUseFootPrint.class){
            System.out.println("锁定当前对象的class1");
        }
    }

    public static void main(String[] args) {
        //sync支持重入
        syncLockClass();
    }





}
