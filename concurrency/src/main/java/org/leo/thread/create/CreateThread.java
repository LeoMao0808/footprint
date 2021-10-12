package org.leo.thread.create;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * 模块名称:创建线程
 * 模块描述:创建线程的方式
 * 1. 继承Thread类
 * 2. 实现Runnable接口
 * 3. 实现Callable接口
 *
 * @author xiaochuang.mao
 * @date 2021/7/2 10:52
 */
public class CreateThread {

    public static void main(String[] args) {
        //继承Thread类
       Thread t1 = new MyThread1("Thread-1");
       t1.start();
        //实现Runnable接口
        Thread t2 = new Thread(new MyThread2(), "Thread-2");
        t2.start();
        //实现Callable接口
        Callable<Long> call = new MyThread3();
        FutureTask<Long> task = new FutureTask<>(call);
        Thread t3 = new Thread(task, "Thread-3");
        t3.start();
        try {
            System.out.println("任务耗时：" + (task.get() / 1000000) + "毫秒");
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }


    static class MyThread1 extends Thread{
        public MyThread1(String name) {
            super(name);
        }
        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName()+"继承Thread创建线程");
        }
    }

    static class MyThread2 implements Runnable {

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName()+"实现Runnable接口创建线程");
        }
    }

    static class MyThread3 implements Callable<Long> {
        private int tickets = 1000;
        @Override
        public Long call() throws Exception {
            long begin = System.nanoTime();
            while(tickets > 0 ){
                System.out.println(Thread.currentThread().getName()+"卖出了第"+ tickets+"张票");
                tickets--;
            }
            long end = System.nanoTime();
            return (end - begin);
        }
    }
}


