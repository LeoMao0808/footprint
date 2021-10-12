package org.leo.aqs;

import java.util.List;
import java.util.Vector;
import java.util.concurrent.Semaphore;
import java.util.function.Function;

/**
 * 模块名称:Semaphore简单用法
 * 核心 :
 *  1.计数器
 *  2.等待队列
 *  3. 三个方法
 *      3.1 init(): 初始化计数器的值
 *      3.2 acquire(): 计数器值 - 1 ,计数器的值 < 0 ? 阻塞,放入等待队列 : 执行;
 *      3.3 release(): 计数器值 + 1 , 计数器值 >= 0 ? 唤醒等待队列头的线程 : 阻塞;
 * Semaphore可以允许多个线程访问一个临界区
 * @author xiaochuang.mao
 * @date 2021/5/11 21:10
 */
public class SemaphoreUseFootPrint {
    private static int count = 0;

    /**
     * 累加器
     * @return void
     */
    public static void totalizer() {
//        初始化
        Semaphore semaphore = new Semaphore(1);
        try {
//            保持互斥
            semaphore.acquire();
            count++;
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            semaphore.release();
        }
    }


    public static void main(String[] args) {
        ObjectPool<Integer, Integer> trObjectPool = new ObjectPool<Integer, Integer>(2, 2);
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                try {
                    trObjectPool.exec(t -> {
                        System.out.println(Thread.currentThread().getName());
                        System.out.println(t);
                        return t;
                    });
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            },"t"+i).start();
        }
    }
}
/**
 * 限流器
 */
class ObjectPool<T,R>{
    final List<T> pool;
    final Semaphore sem;

    /**
     * 初始化
     * @return
     */
    ObjectPool(Integer size ,T t) {
        pool = new Vector<T>();
        for (int i = 0; i < size+10; i++) {
            pool.add(t);
        }
        sem = new Semaphore(size);
    }

    R exec(Function<T,R> func) throws InterruptedException {
        T t = null;
        sem.acquire();
        try {
            t = pool.remove(0);
            return func.apply(t);
        } finally {
            pool.add(t);
            sem.release();
        }
    }

}
