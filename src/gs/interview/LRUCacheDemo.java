package gs.interview;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class LRUCacheDemo{

    public static void main(String[] args) {
        LRUCache lruCache = new LRUCache(3);
        lruCache.set(1,9);
        lruCache.set(2,8);
        lruCache.set(3,7);
        lruCache.set(2,8);
        lruCache.set(4,6);
        lruCache.set(2,8);
        lruCache.set(5,5);

        System.out.println(lruCache.get(1));
        System.out.println(lruCache.get(2));
        System.out.println(lruCache.get(3));
        System.out.println(lruCache.get(4));
        System.out.println(lruCache.get(5));
    }

}

class LRUCache {

    private Queue<Integer> queue;
    private Map<Integer,Integer> map;
    private int size;

    public LRUCache(int size){
        this.queue = new LinkedList<>();
        this.map = new HashMap<>();
        this.size = size;
    }

    public void set(int key, int value){
        if(this.map.containsKey(key)){
            this.queue.remove(key);
        }
        if(this.queue.size() == size)
            this.map.remove(this.queue.poll());
        this.queue.offer(key);
        this.map.put(key, value);
    }

    public int get(int key){
        int result = -1;
        if(this.map.containsKey(key)){
            result = this.map.get(key);
            this.queue.remove(key);
            this.queue.offer(key);
        }
        return result;
    }
}

