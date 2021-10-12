package org.leo.thread.stop;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

/**
 * 模块名称:安全停止线程的方式
 * 模块描述:
 * 1.使用volatile修饰变量作为线程结束标识符
 * 2.使用 interrupt 方法和 Thread.interrupted 方法配合使用来控制线程终止
 *
 * @author xiaochuang.mao
 * @date 2021/7/6 16:53
 */
public class SafeStopThread {
    public static void main(String[] args) throws InterruptedException {
        //使用volatile修饰变量作为线程结束标识符
        MyTask1 task1 = new MyTask1();
        Thread t1 = new Thread(task1, "mytask1");
        t1.start();
        TimeUnit.SECONDS.sleep(10);
        task1.cancel();

        //使用 interrupt 方法和 Thread.interrupted 方法配合使用来控制线程终止
        MyTask2 task2 = new MyTask2();
        Thread t2 = new Thread(task2, "mytask2");
        t2.start();
        TimeUnit.SECONDS.sleep(2);
        t2.interrupt();
    }


    private static class MyTask1 implements Runnable{
        private volatile boolean flag = true;
        private volatile AtomicLong count = new AtomicLong(0L);
        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName()+"线程启动");
            while (flag) {
                System.out.println(count.incrementAndGet());
            }
            System.out.println(Thread.currentThread().getName() + "线程终止");

        }

        public void cancel(){
            flag = false;
        }
    }
    private static class MyTask2 implements Runnable{
        private volatile AtomicLong count = new AtomicLong(0L);
        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName()+"线程启动");
            while (!Thread.interrupted()) {
                System.out.println(count.incrementAndGet());
            }
            System.out.println(Thread.currentThread().getName() + "线程终止");

        }
    }




}
