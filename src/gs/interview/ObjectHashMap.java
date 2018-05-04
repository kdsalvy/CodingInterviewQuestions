package gs.interview;

import java.util.HashMap;
import java.util.Map;

public class ObjectHashMap {

    Map<String, String> map;

    public ObjectHashMap(){
        map = new HashMap<>();
        /* For map with too many entries out of memory exception will come*/
        for(int i = 1; i < 100000; i++)
            map.put("A" + i, "A" + i);
        ObjectHashMap ohm1 = new ObjectHashMap();
    }

    public static void main (String[] args)
    {
        ObjectHashMap ohm = new ObjectHashMap();
    }
}
