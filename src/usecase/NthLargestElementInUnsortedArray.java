package usecase;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

public class NthLargestElementInUnsortedArray {
    public static void main(String[] args) {
        Integer[] arr = {2, 5, 3, 7, 1, 6, 9};
        int n = 4;
        System.out.println(findNthLargestNumber(arr,n));
    }

    public static int findNthLargestNumber(Integer[] arr, int n) {
        List<Integer> list = Arrays.stream(arr).sorted(Comparator.reverseOrder()).collect(Collectors.toList());
        int count = 0;
        Iterator<Integer> iterator = list.iterator();
        while (iterator.hasNext()) {
            count++;
            int curr = iterator.next();
            if (count == n)
                return curr;
        }
        return -1;
    }
}
