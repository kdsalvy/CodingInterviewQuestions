package usecase;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;
import java.util.concurrent.Semaphore;

public class ProducerConsumerProblemUsingSemaphore {

    public Queue<Integer> repository = null;
    public static Semaphore producerSemaphore= null;
    public static Semaphore consumerSemaphore = null;
    public int MAX_SIZE = 0;

    public ProducerConsumerProblemUsingSemaphore() {
        repository = new LinkedList<>();
        producerSemaphore = new Semaphore(1);
        consumerSemaphore = new Semaphore(0);
        MAX_SIZE = 10;
    }

    public static void main(String[] args) {

        ProducerConsumerProblemUsingSemaphore pcS = new ProducerConsumerProblemUsingSemaphore();
        Producer producer = pcS.new Producer();
        Consumer consumer = pcS.new Consumer();

        Thread pThread = new Thread(producer);
        Thread cThread = new Thread(consumer);

        pThread.start();
        cThread.start();
    }

    public class Producer implements Runnable {

        @Override
        public void run() {
            int i = 0;
            while (true) {
                try {
                    //Thread.sleep(5000);
                    producerSemaphore.acquire();
                    System.out.println(Thread.currentThread().getName() + ": Produced: " + ++i);
                    repository.offer(i);
                    consumerSemaphore.release();
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }

    public class Consumer implements Runnable {

        @Override
        public void run() {
            while (true) {
                try {
                    consumerSemaphore.acquire();
                    Integer n = repository.poll();
                    System.out.println(Thread.currentThread().getName() + ": Consumed: " + n);
                    producerSemaphore.release();
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }
}
