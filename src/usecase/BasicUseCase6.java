package usecase;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Sort HashMap based on values using Java 8 stream APIs.
 */
public class BasicUseCase6 {

    public static void main(String[] args){
        Map<Integer,Integer> intMap = new HashMap<>();
        Random random = new Random();
        for(int i = 1; i <= 10; i++)
            intMap.put(i, random.nextInt(10));

        System.out.println("UnsortedMap: " + intMap);
        List sortedValueList = sortMapByValuesInDecreasingOrder(intMap);
        System.out.println("SortedValueList: " + sortedValueList);
    }

    public static List<Integer> sortMapByValuesInDecreasingOrder(Map<Integer, Integer> map){
        List<Integer> list = map.values().parallelStream().sorted(Comparator.reverseOrder()).collect(Collectors.toList());
        return list;
    }
}
