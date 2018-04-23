package gs.interview;

public class ThreeThreadSynchronization {

    volatile int i = 1;
    volatile int t1Count = 0;
    volatile int t2Count = 0;
    volatile boolean t1 = true;
    volatile boolean t2 = false;
    volatile boolean t3 = false;

    public void incrementByThread1() {
        while(true) {
            synchronized (this) {
                while (!t1 && (t2 || t3)) {
                    try {
                        this.wait();
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
                }
                System.out.println(Thread.currentThread().getName() + ": " + i);
                t1Count = i;
                i++;
                t1 = false;
                t2 = true;
                t3 = false;
                this.notifyAll();
            }
        }
    }

    public void incrementByThread2(){
        while(true) {
            synchronized (this) {
                while (!t2 && (t1 || t3)) {
                    try {
                        this.wait();
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
                System.out.println(Thread.currentThread().getName() + ": " + i);
                t2Count = i;
                i++;
                t2 = false;
                t3 = true;
                t1 = false;
                this.notifyAll();
            }
        }
    }

    public void productByThread3(){
        while(true) {
            synchronized (this) {
                while (!t3 && (t1 || t2)) {
                    try {
                        this.wait();
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
                System.out.println(Thread.currentThread().getName() + ": " + (t1Count * t2Count));
                t3 = false;
                t1 = true;
                t2 = false;
                this.notifyAll();
            }
        }
    }

    public static void main(String[] args){
        ThreeThreadSynchronization tts = new ThreeThreadSynchronization();
        Thread thread1 = new Thread(() -> tts.incrementByThread1());

        Thread thread2 = new Thread(() -> tts.incrementByThread2());

        Thread thread3 = new Thread(() -> tts.productByThread3());

        thread1.start();
        thread2.start();
        thread3.start();

    }
}
