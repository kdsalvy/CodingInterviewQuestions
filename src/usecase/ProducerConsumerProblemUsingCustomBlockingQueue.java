package usecase;

import java.util.LinkedList;
import java.util.Queue;

public class ProducerConsumerProblemUsingCustomBlockingQueue {
    public class CustomBlockingQueue {
        private Queue<Integer> queue = null;
        volatile int capacity = 0;

        public CustomBlockingQueue(int capacity) {
            queue = new LinkedList<>();
            this.capacity = capacity;
        }

        public void put(int data) {
            synchronized (this) {
                try {
                    if (queue.size() >= capacity)
                        this.wait();
                    queue.offer(data);
                    this.notifyAll();
                } catch (InterruptedException ex) {
                    System.out.println("Exception in Producer: " + ex.getMessage());
                }
            }
        }

        public int take() {
            // Remove data once data is available
            int data = -1;
            synchronized (this) {
                try {
                    //Thread.sleep(1000);
                    if (queue.isEmpty())
                        this.wait();
                    data = queue.poll();
                    this.notifyAll();
                } catch (Exception ex) {
                    System.out.println("Exception in Consumer: " + ex.getMessage());
                }
            }
            return data;
        }
    }


    public class Producer implements Runnable {

        CustomBlockingQueue queue = null;
        Integer data = 0;

        public Producer(CustomBlockingQueue queue) {
            this.queue = queue;
            this.data = 1;
        }

        @Override
        public void run() {
            while (true) {
                System.out.println(Thread.currentThread().getName() + ": Produced: " + data);
                queue.put(data++);
            }
        }
    }

    public class Consumer implements Runnable {

        CustomBlockingQueue queue = null;

        public Consumer(CustomBlockingQueue queue) {
            this.queue = queue;
        }

        @Override
        public void run() {
            while (true)
                System.out.println(Thread.currentThread().getName() + ": Consumed: " + queue.take());
        }
    }

    public static void main(String[] args) {
        ProducerConsumerProblemUsingCustomBlockingQueue pcp = new ProducerConsumerProblemUsingCustomBlockingQueue();
        CustomBlockingQueue queue = pcp.new CustomBlockingQueue(10);

        Producer producer = pcp.new Producer(queue);
        Consumer consumer = pcp.new Consumer(queue);

        Thread producerThread = new Thread(producer);
        Thread consumerThread = new Thread(consumer);

        producerThread.start();
        consumerThread.start();

    }
}
