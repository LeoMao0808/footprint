package org.leo.sync;

/**
 * 模块名称:CRM
 * 模块描述:TODO
 *
 * @author xiaochuang.mao
 * @date 2021/7/8 16:28
 */
public class TestSync {
    private volatile int count = 1;

    public static void main(String[] args) {
        synchronized (new TestSync()){
            System.out.println("同步块");
        }
    }

    public synchronized void test(){
        System.out.println("同步块1");
    }

    public synchronized static void test1(){
        System.out.println("同步块1");
    }
}
