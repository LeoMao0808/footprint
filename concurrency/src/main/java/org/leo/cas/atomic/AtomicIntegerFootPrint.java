package org.leo.cas.atomic;

import com.sun.xml.internal.bind.v2.runtime.output.SAXOutput;
import sun.misc.Unsafe;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 模块名称:原子类-AtomicInteger
 * 模块描述:AtomicInteger使用
 *
 * @author xiaochuang.mao
 * @date 2021/7/28 19:05
 */
public class AtomicIntegerFootPrint {
   public static AtomicInteger count = new AtomicInteger(1);

    public static void main(String[] args) {
        //获取当前值
        System.out.println(count.get());//1
        try {
            System.out.println(Unsafe.getUnsafe().objectFieldOffset(AtomicInteger.class.getDeclaredField("value")));
        } catch (Exception ex) { throw new Error(ex); }
        //获取当前值然后加上指定值
        System.out.println(count.getAndAdd(10));//1
        System.out.println(count.get());//11
        //获取当前值然后初始化为指定值
        System.out.println(count.getAndSet(10));//11
        System.out.println(count.get());//10
        //获取当前值然后自增1
        System.out.println(count.getAndIncrement());//10
        System.out.println(count.get()); //11
        //获取当前值然后自减1
        System.out.println(count.getAndDecrement());//11
        System.out.println(count.get());//10
        //自增1再获取当前值
        System.out.println(count.incrementAndGet());//11
        // 自减1再获取当前值
        System.out.println(count.decrementAndGet());//10
        //增加指定值然后获取当前值
        System.out.println(count.addAndGet(12));//22

    }
}
