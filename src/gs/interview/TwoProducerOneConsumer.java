package gs.interview;

import java.util.concurrent.*;

public class TwoProducerOneConsumer {

    BlockingQueue<Integer> queue = null;
    final CyclicBarrier cb;

    public TwoProducerOneConsumer(){
        queue = new LinkedBlockingQueue<>();
        cb = new CyclicBarrier(2);
    }

    public static void main(String[] args){
        TwoProducerOneConsumer tpoc = new TwoProducerOneConsumer();
        ExecutorService executor = Executors.newFixedThreadPool(3);
        executor.execute(new OddProducer(tpoc.cb, tpoc.queue));
        executor.execute(new EvenProducer(tpoc.cb, tpoc.queue));
        executor.execute(new ProductConsumer(tpoc.queue));
        executor.shutdown();
    }
}

class EvenProducer implements Runnable{
    CyclicBarrier cb = null;
    BlockingQueue<Integer> queue = null;

    public EvenProducer(CyclicBarrier cb, BlockingQueue<Integer> queue){
        this.cb = cb;
        this.queue = queue;
    }

    @Override
    public void run(){
        for(int i = 2; i <= 10; i+=2) {
            queue.offer(i);
            try {
                cb.await();
            } catch (InterruptedException | BrokenBarrierException ex){
                ex.printStackTrace();
            }
        }
    }
}

class OddProducer implements Runnable{
    CyclicBarrier cb = null;
    BlockingQueue<Integer> queue = null;

    public OddProducer(CyclicBarrier cb, BlockingQueue<Integer> queue){
        this.cb = cb;
        this.queue = queue;
    }

    @Override
    public void run(){
        for(int i = 1; i <= 10; i+=2) {
            queue.offer(i);
            try {
                cb.await();
            } catch (InterruptedException | BrokenBarrierException ex){
                ex.printStackTrace();
            }
        }
    }
}

class ProductConsumer implements Runnable{
    BlockingQueue<Integer> queue = null;

    public ProductConsumer(BlockingQueue<Integer> queue){
        this.queue = queue;
    }

    @Override
    public void run(){
        for(int i = 0; i < 5; i++){
            int a = queue.poll();
            int b = queue.poll();
            System.out.println(a + " * " + b + " = " + (a*b));
        }
    }
}