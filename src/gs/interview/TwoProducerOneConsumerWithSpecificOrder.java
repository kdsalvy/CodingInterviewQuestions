package gs.interview;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.*;

/**
 * Design and write code For two system which are generating message and one system
 * is consuming message with below constraints
 * i) Consumer will consume message when both system done producing.
 * ii) Ordering oF message is important.
 * Ex. IF p1 produces msg1, msg2, msg10, msg11, and p2 produces msg3, msg4, msg 20, msg 21 then consumer will
 * consume msg1 and msg3 ±rst then msg2 and msg 4
 */
public class TwoProducerOneConsumerWithSpecificOrder {

    public static void main(String[] args){
        Queue<String> queue = new LinkedList<>();
        int counter = 1;
        MessageConsumer consumer = new MessageConsumer(queue);

        // the second argument in cyclic barrier's constructor is the action
        // the last thread will perform once the barrier is tripped.
        final CyclicBarrier cb = new CyclicBarrier(2, consumer);
        Object lock = new Object();
        MessageGenerator generator1 = new MessageGenerator(queue, cb, counter, lock);
        MessageGenerator generator2 = new MessageGenerator(queue, cb, counter, lock);

        ExecutorService executor = Executors.newFixedThreadPool(2);
        executor.execute(generator1);
        executor.execute(generator2);

        executor.shutdown();
    }
}

class MessageGenerator implements Runnable{
    private Queue<String> queue;
    private CyclicBarrier cb;
    private static volatile int counter;
    Object lock;

    public MessageGenerator(Queue<String> queue, CyclicBarrier cb, int counter, Object lock){
        this.queue = queue;
        this.cb = cb;
        this.counter = counter;
        this.lock = lock;
    }

    @Override
    public void run(){
        while(true){
            try {
                Thread.sleep(1000);
                synchronized (lock) {
                    queue.offer(Thread.currentThread().getName() + ": Msg" + counter++);
                }
                cb.await();
            } catch (Exception ex){
                ex.printStackTrace();
            }
        }
    }
}

class MessageConsumer implements Runnable{
    private Queue<String> queue;

    public MessageConsumer(Queue<String> queue){
        this.queue = queue;
    }

    @Override
    public void run(){
        try {
            Thread.sleep(1000);
            System.out.println("Consumes: " + queue.poll());
            System.out.println("Consumes: " + queue.poll());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}