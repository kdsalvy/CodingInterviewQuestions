package gs.interview;

public class ThreeThreadSynchronization {

    volatile int i = 1;
    volatile int t1Count = 0;
    volatile int t2Count = 0;
    volatile boolean t1 = true;
    volatile boolean t2 = false;
    volatile boolean t3 = false;

    public void incrementByThread1() {
        synchronized (this) {
            while (!t1){
                try {
                    wait();
                } catch(InterruptedException ex){
                    ex.printStackTrace();
                }
            }
            System.out.println(Thread.currentThread().getName() + ": " + i);
            t1Count = i;
            i++;
            t1 = false;
            t2 = true;
            notifyAll();
        }
    }

    public void incrementByThread2(){
        synchronized (this){
            while(!t2){
                try{
                    wait();
                } catch(Exception ex){
                    ex.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + ": " + i);
                t2Count = i;
                i++;
                t2 = false;
                t3 = true;
                notifyAll();
            }
        }
    }

    public void productByThread3(){
        synchronized (this){
            while(!t3){
                try{
                    wait();
                } catch(Exception ex){
                    ex.printStackTrace();
                }
            }
            System.out.println(Thread.currentThread().getName() +": " + (t1Count * t2Count));
            t3 = false;
            t1 = true;
            notifyAll();
        }
    }

    public static void main(String[] args){
        ThreeThreadSynchronization tts = new ThreeThreadSynchronization();
        Thread thread1 = new Thread(new Runnable(){
           @Override
           public void run(){
               tts.incrementByThread1();
           }
        });

        Thread thread2 = new Thread(new Runnable(){
           @Override
           public void run(){
               tts.incrementByThread2();
           }
        });

        Thread thread3 = new Thread(new Runnable(){
            @Override
            public void run(){
                tts.productByThread3();
            }
        });

        thread1.start();
        thread2.start();
        thread3.start();

    }
}
