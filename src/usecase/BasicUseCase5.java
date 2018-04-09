package usecase;

import java.util.HashMap;
import java.util.Map;

/**
 *  Find fibonacci number for a given index in the fibonacci series
 */
public class BasicUseCase5 {

    public static void main(String[] args){
        BasicUseCase5 fs = new BasicUseCase5();
        int number = 5;
        Map<Integer, Integer> map = new HashMap<>();
        System.out.println("Recursive Solution: " + fs.fibonacciNumberRecursive(number, map));
        System.out.println("Iterative Solution: " + fs.fibonacciNumberIterative(number));
    }

    /**
     * Recursive Method to find the fibonacci number for a given index
     * @param number
     * @param map
     * @return int
     */
    public int fibonacciNumberRecursive(int number, Map<Integer, Integer> map){
        if(number == 0)
            return 0;
        if(number == 1)
            return 1;

        if(map.containsKey(number))
            return map.get(number);

        int res = fibonacciNumberRecursive(number - 1, map) + fibonacciNumberRecursive( number - 2, map);
        map.put(number, res);
        return res;
    }

    /**
     * Iterative Method to find the fibonacci number for a given index
     * @param number
     * @return int
     */
    public int fibonacciNumberIterative(int number){
        if(number == 0)
            return 0;
        if(number == 1)
            return 1;
        int num1 = 0;
        int res = 1;
        for(int i = 2; i <= number; i++){
            int temp = res;
            res += num1;
            num1 = temp;
    }
        return res;
    }
}
