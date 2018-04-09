package usecase;

public class CustomCyclicBarrier {
    private final Object LOCK;
    private volatile int count;
    private volatile int maxCount;

    public CustomCyclicBarrier(int maxCount) {
        this.LOCK = new Object();
        this.maxCount = maxCount;
        this.count = 0;
    }

    public void acquire() throws InterruptedException {
        synchronized (LOCK) {
            count++;
            if (count == maxCount) {
                LOCK.notifyAll();
                count = 0;
            } else {
                LOCK.wait();
            }
        }
    }
}
