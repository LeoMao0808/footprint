package org.leo.sync;

/**
 * 模块名称:Volatile的使用以及作用
 * 禁止指令重排以及保证可见性,但是它不能保证原子性
 *
 * @author xiaochuang.mao
 * @date 2021/5/11 20:57
 */
public class VolatileUseFootPrint {
    /**这里不加volatile也好使 why */
    volatile boolean running = true;

 /*   public void test(){
        System.out.println("test start...");
        while (running){
            System.out.println("test running...");
        }
        System.out.println("test end...");
    }

    public void change(){
        running = false;
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("changed...");
    }
    public static void main(String[] args) {
        VolatileUseFootPrint demo = new VolatileUseFootPrint();
        new Thread(demo :: test,"t1").start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new Thread(()->System.out.println("test"),"temp").start();
        new Thread(demo :: change,"t2").start();
    }*/
}
