package org.leo.thread.baseapi;

/**
 * 模块名称:Thread相关操作练习
 * 模块描述:TODO
 *
 * @author xiaochuang.mao
 * @date 2021/7/6 10:47
 */
public class ThreadOperate {
    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            try {
                //线程睡眠指定毫秒数,线程状态从Running转换成Waiting
                //该线程如果持有锁,此方法不会释放锁
                //睡眠指定时间超时后,重新转换状态为Runnable
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
            //当前线程让出cpu执行时间,并建议同一优先级或者更高优先级的线程执行
            Thread.yield();
            while (true){
                System.out.println("HELLO WORLD不必须执行");
            }

        },"Thread-2");
        t2.start();
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        t1.start();
        try {
            t1.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("main方法执行结束!");
    }

}
