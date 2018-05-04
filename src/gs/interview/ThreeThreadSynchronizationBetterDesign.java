package gs.interview;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class ThreeThreadSynchronizationBetterDesign {

    public static void main(String[] args){

        final Semaphore s1 = new Semaphore(1);
        final Semaphore s2 = new Semaphore(0);
        final Semaphore s3 = new Semaphore(0);

        ExecutorService executor = Executors.newFixedThreadPool(3);
        executor.execute(new FirstCountingThread(s1, s2));
        executor.execute(new SecondCountingThread(s2, s3));
        executor.execute(new ThirdCountingThread(s3, s1));
        executor.shutdown();
    }
}

class FirstCountingThread implements Runnable{

    private Semaphore s1;
    private Semaphore s2;

    public FirstCountingThread(Semaphore s1, Semaphore s2){
        this.s1 = s1;
        this.s2 = s2;
    }

    @Override
    public void run(){
        for(int i = 1; i <= 10; i++){
            try{
                s1.acquire();
                System.out.println("A" + i);
                s2.release();
            } catch(Exception ex){
                ex.printStackTrace();
            }
        }
    }
}

class SecondCountingThread implements Runnable{
    private Semaphore s2;
    private Semaphore s3;

    public SecondCountingThread(Semaphore s2, Semaphore s3){
        this.s2 = s2;
        this.s3 = s3;
    }

    @Override
    public void run(){
        for(int i = 1; i <= 10; i++){
            try{
                s2.acquire();
                System.out.println("B" + i);
                s3.release();
            } catch(Exception ex){
                ex.printStackTrace();
            }
        }
    }
}

class ThirdCountingThread implements Runnable{
    private Semaphore s3;
    private Semaphore s1;

    public ThirdCountingThread(Semaphore s3, Semaphore s1){
        this.s3 = s3;
        this.s1 = s1;
    }

    @Override
    public void run(){
        for(int i = 1; i <= 10; i++){
            try{
                s3.acquire();
                System.out.println("C" + i);
                s1.release();
            } catch(Exception ex){
                ex.printStackTrace();
            }
        }
    }
}