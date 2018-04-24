package gs.interview;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ExecutorServiceImpl {
    public static void main(String[] args){
        ExecutorService executor = Executors.newFixedThreadPool(2);
        List<Future<Integer>> fObjList = new ArrayList<>();
        fObjList.add (executor.submit(new CallableImpl(3000)));
        fObjList.add (executor.submit(new CallableImpl(5000)));
        fObjList.add (executor.submit(new CallableImpl(10000)));
        fObjList.add (executor.submit(new CallableImpl(15000)));

        try {
            while(!fObjList.isEmpty()){
                Iterator<Future<Integer>> itr = fObjList.iterator();
                while(itr.hasNext()){
                    Future<Integer> cur = itr.next();
                    if(cur.isDone()){
                        Integer res = cur.get();
                        persistToDB(res);
                        itr.remove();
                    }
                }
            }
        } catch(Exception ex){
            ex.printStackTrace();
        }

        executor.shutdown();
    }

    public static void persistToDB(Integer res){
        System.out.println("Persisting in db: " + res);
    }
}

class CallableImpl implements Callable<Integer>{

    int timeoutInMillis;

    public CallableImpl(int timeoutInMillis){
        this.timeoutInMillis = timeoutInMillis;
    }

    @Override
    public Integer call(){
        try {
            Thread.sleep(timeoutInMillis);
        }catch(InterruptedException ex){
            ex.printStackTrace();
        }
        return timeoutInMillis;
    }
}
