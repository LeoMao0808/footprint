package org.leo.thread.baseapi;

/**
 * 模块名称:Thread基础API
 * 模块描述:Thread的基础API学习
 *
 * @author xiaochuang.mao
 * @date 2021/7/2 14:10
 */
public class BaseApiThread {
    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("HELLO");
        });
        Thread t2 = new Thread(() -> {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("HELLO WORLD");
        },"Thread-2");

        //设置线程名称
        t1.setName("Thread-1");
        //设置优先级范围是[1,10],默认5
        t1.setPriority(Thread.MAX_PRIORITY);
        //设置线程为守护线程
        t1.setDaemon(true);
        //中断t1的运行状态
        t1.interrupt();
        //线程启动
        t1.start();
        t2.start();
        //获取当前正在运行的线程
        System.out.println(Thread.currentThread());
        //判断当前线程是否中断
        boolean interrupted = Thread.interrupted();
        //判断线程是否中断
        boolean isInterrupted = t1.isInterrupted();
        //获取线程优先级
        int priority = t1.getPriority();
        //获取线程名称
        String name = t1.getName();
        //判断当前线程是否为守护线程
        System.out.println(t1.isDaemon());
    }

}
