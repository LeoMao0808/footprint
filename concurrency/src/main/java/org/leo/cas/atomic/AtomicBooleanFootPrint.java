package org.leo.cas.atomic;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * 模块名称:AtomicBoolean
 * 模块描述: AtomicBoolean的使用
 *
 * @author xiaochuang.mao
 * @date 2021/7/29 15:37
 */
public class AtomicBooleanFootPrint {
    private static AtomicBoolean flag = new AtomicBoolean(false);

    public static void main(String[] args) {
        //获取当前值
        System.out.println(flag.get());//false
        //获取当前值然后设置指定值
        System.out.println(flag.getAndSet(true));//false
        System.out.println(flag.get());//true
        flag.set(false);
        System.out.println(flag.get());//true
        flag.lazySet(true);
        System.out.println(flag.get());//true


    }
}
