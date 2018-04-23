package gs.interview;

public class OddEvenUsingTwoThreads {

    boolean odd;
    int count = 1;
    int max = 20;

    public void printOdd(){
        synchronized (this){
            while(count < max){
                while(!odd){
                    try{
                        this.wait();
                    } catch(InterruptedException ex){
                        ex.printStackTrace();
                    }
                }
                System.out.println(Thread.currentThread().getName() + ": " + count + " ");
                count++;
                odd = false;
                this.notify();
            }
        }
    }

    public void printEven(){
        synchronized (this) {
            while (count < max) {
                while (odd) {
                    try {
                        this.wait();
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
                }
                System.out.println(Thread.currentThread().getName() + ": " + count + " ");
                count++;
                odd = true;
                this.notify();
            }
        }
    }

    public static void main(String[] args){
        OddEvenUsingTwoThreads oet = new OddEvenUsingTwoThreads();
        oet.odd = true;
        Thread oddThread = new Thread(new Runnable() {
            @Override
            public void run() {
                oet.printOdd();
            }
        });
        Thread evenThread = new Thread(new Runnable() {
            @Override
            public void run() {
                oet.printEven();
            }
        });

        oddThread.start();
        evenThread.start();
    }
}
