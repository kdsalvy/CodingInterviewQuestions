package usecase;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class CustomThreadPool {

    private BlockingQueue<Runnable> tasksQueue = null;
    private List<CustomPoolThread> threadList = null;
    private boolean isStopped = false;

    public CustomThreadPool(int maxThreadCount, int maxTaskCount){
        tasksQueue = new LinkedBlockingQueue<>(maxTaskCount);
        threadList = new ArrayList<>();
        for(int i = 0; i < maxThreadCount; i++){
            threadList.add(new CustomPoolThread(tasksQueue));
        }

        for(CustomPoolThread thread: this.threadList)
            thread.start();
    }

    public synchronized void execute(Runnable task){
        if(this.isStopped)
            throw new IllegalStateException("ThreadPool Is Stopped");
        tasksQueue.offer(task);
    }

    public synchronized void doStop(){
        this.isStopped = true;
        for(CustomPoolThread thread: this.threadList){
            thread.doStop();
        }
    }

    class CustomPoolThread extends Thread{
        private BlockingQueue<Runnable> tasksQueue = null;
        private boolean isStopped = false;

        public CustomPoolThread(BlockingQueue<Runnable> tasksQueue){
            this.tasksQueue = tasksQueue;
        }

        @Override
        public void run(){
            while(!isStopped()){
                try {
                    Runnable runnable = tasksQueue.poll();
                    runnable.run();
                } catch(Exception ex){
                    ex.printStackTrace();
                }
            }
        }

        public synchronized void doStop(){
            this.isStopped = true;
        }

        public synchronized  boolean isStopped(){
            return this.isStopped;
        }


    }
}
