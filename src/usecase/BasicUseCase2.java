package usecase;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Implement Producer Consumer problem having 10 producers and 10 consumers
 */
public class BasicUseCase2 {

    protected static Object _LOCK = null;
    Queue<Integer> queue = null;
    int size = 0;

    public BasicUseCase2() {
        _LOCK = new Object();
        queue = new LinkedList<>();
        size = 10;
    }

    public static void main(String[] args) {
        BasicUseCase2 pcp = new BasicUseCase2();
        Producer producer = pcp.new Producer();
        Consumer consumer = pcp.new Consumer();

        // create 10 producers and consumers
        for (int i = 0; i < 10; i++) {
            Thread producerThread = new Thread(producer);
            Thread consumerThread = new Thread(consumer);
            producerThread.start();
            consumerThread.start();
        }
    }

    /**
     * Producer Class
     */
    public class Producer implements Runnable {
        Integer packet = null;

        public Producer() {
            packet = 1;
        }

        @Override
        public void run() {
            synchronized (_LOCK) {
                while (true) {
                    try {
                        if (queue.size() < size) {
                            System.out.println(Thread.currentThread().getName() + " : Produced Packet: " + packet);
                            queue.offer(packet++);
                            _LOCK.notifyAll();
                        } else {
                            _LOCK.wait();
                        }
                    } catch (InterruptedException ex) {
                        System.out.println("Thread Interrupted");
                        ex.printStackTrace();
                    }
                }
            }
        }
    }

    /**
     * Consumer Class
     */
    public class Consumer implements Runnable {
        @Override
        public void run() {
            synchronized (_LOCK) {
                while (true) {
                    try {
                        //Thread.sleep(1000);
                        if (queue.size() > 0) {
                            System.out.println(Thread.currentThread().getName() + " : Consumed Packet: " + queue.poll());
                            _LOCK.notifyAll();
                        } else {
                            _LOCK.wait();
                        }
                    } catch (InterruptedException ex) {
                        System.out.println("Thread Interrupted");
                        ex.printStackTrace();
                    }
                }
            }
        }
    }
}
