package usecase;

public class CustomCountDownLatch {

    private final Object LOCK;
    private volatile int count;

    public CustomCountDownLatch(int count) {
        LOCK = new Object();
        this.count = count;
    }

    public void acquire() throws InterruptedException {
        synchronized (LOCK) {
            while (count != 0) {
                LOCK.wait();
            }
        }
    }

    public void countdown() throws InterruptedException{
        synchronized (LOCK){
            count--;
            if(count == 0)
                LOCK.notifyAll();
        }
    }

}
