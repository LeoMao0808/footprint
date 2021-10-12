package org.leo.aqs;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 模块名称:Condition
 * 模块描述:Condition用于解决同步问题
 * 内置锁（synchronized）配合内置条件队列（wait、notify、notifyAll ），显式锁（Lock）配合显式条件队列（Condition ）
 * await、signal、signalAll 与 wait、notify、notifyAll 相对应.
 * 每个锁（Lock）上可以存在多个 Condition，这意味着锁的状态条件可以有多个。
 * 支持公平的或非公平的队列操作。
 * 支持可中断的条件等待，相关方法：awaitUninterruptibly() 。
 * 支持可定时的等待，相关方法：awaitNanos(long) 、await(long, TimeUnit)、awaitUntil(Date)。
 * @author xiaochuang.mao
 * @date 2021/7/15 15:26
 */
public class ConditionUseFootPrint {
    public static void main(String[] args) {
        Message message = new Message();
        Thread producer = new Thread(new MessageProducer(message));
        Thread consumer = new Thread(new MessageConsumer(message));
        producer.start();
        consumer.start();
    }
}
/**
 * 消息类
 */
class Message{
    private String msg;
    private boolean state;
    private boolean end;
    private final Lock lock =  new ReentrantLock();
    private final Condition producedMsg = lock.newCondition();
    private final Condition consumedMsg = lock.newCondition();
    /**
     * 消费动作
     */
    public void consume(){
        lock.lock();
        try{
            //消费者等待消息消费
            while(!state){
                producedMsg.await();
            }
            state = false;
            System.out.println("consume message : " + msg);
            consumedMsg.signal();
        }catch (InterruptedException  e){
            System.out.println("Thread interrupted - publishMessage");
        }finally {
            lock.unlock();
        }

    }

    /**
     * 生产动作
     */
    public void produce(String message){
        lock.lock();
        try{
            //消费者等待消息消费
            while(state){
                consumedMsg.await();
            }
            System.out.println("produce message : " + message);
            state = true;
            msg = message;
            producedMsg.signal();
        }catch (InterruptedException  e){
            System.out.println("Thread interrupted - publishMessage");
        }finally {
            lock.unlock();
        }
    }
    public boolean isEnd(){
        return end;
    }

    public void setEnd(boolean end) {
        this.end = end;
    }
}
/**
 * 消息消费者
 */
class MessageConsumer implements Runnable {
    private Message message ;

    public MessageConsumer(Message message) {
        this.message = message;
    }

    @Override
    public void run() {
        while (!message.isEnd()){
            message.consume();
        }
    }
}
/**
 * 消息生产者
 */
class MessageProducer implements Runnable {
    private final Message message;

    public MessageProducer(Message message) {
        this.message = message;
    }

    @Override
    public void run() {
        produce();
    }
    private void produce(){
        Arrays.asList("begin","msg1","msg2","end").forEach(o ->{
            message.produce(o);
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        message.setEnd(true);
    }
}
