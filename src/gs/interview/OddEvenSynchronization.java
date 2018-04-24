package gs.interview;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class OddEvenSynchronization {

    public static void main(String[] args){
        Semaphore oddSemaphore = new Semaphore(1);
        Semaphore evenSemaphore = new Semaphore(0);
        ExecutorService executor = Executors.newFixedThreadPool(2);
        executor.execute(new OddNumberPrintingThread(oddSemaphore, evenSemaphore, 10));
        executor.execute(new EvenNumberPrintingThread(evenSemaphore, oddSemaphore, 10));
        executor.shutdown();
    }
}

class OddNumberPrintingThread implements Runnable{
    Semaphore oddSemaphore = null;
    Semaphore evenSemaphore = null;
    int num;
    public OddNumberPrintingThread(Semaphore oddSemaphore, Semaphore evenSemaphore, int num){
        this.oddSemaphore = oddSemaphore;
        this.evenSemaphore = evenSemaphore;
        this.num = num;
    }

    @Override
    public void run(){
        for(int i = 1; i <= num; i+=2) {
            try {
                oddSemaphore.acquire();
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
            System.out.println("A" + i);
            evenSemaphore.release();
        }
    }
}

class EvenNumberPrintingThread implements Runnable{
    Semaphore evenSemaphore = null;
    Semaphore oddSemaphore = null;
    int num;
    public EvenNumberPrintingThread(Semaphore evenSemaphore, Semaphore oddSemaphore, int num){
        this.evenSemaphore = evenSemaphore;
        this.oddSemaphore = oddSemaphore;
        this.num = num;
    }

    @Override
    public void run(){
        for(int i = 2; i <= num; i+=2) {
            try {
                evenSemaphore.acquire();
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
            System.out.println("B" + i);
            oddSemaphore.release();
        }
    }
}