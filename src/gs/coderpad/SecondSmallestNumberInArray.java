package gs.coderpad;

public class SecondSmallestNumberInArray {
    public static void main(String[] args) {
        int[] arr = {1, 1, 2, 1, 1, 1};
        System.out.println(findSecondSmallestNumber(arr));
    }

    /**
     * Finds the second smallest number from the array
     * @param arr input array
     * @return second smallest number
     */
    public static int findSecondSmallestNumber(int[] arr) {
        int smallest = Integer.MAX_VALUE;
        int secondSmallest = Integer.MAX_VALUE;

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] < smallest) {
                secondSmallest = smallest;
                smallest = arr[i];
            } else if(arr[i] < secondSmallest && arr[i] != smallest){
                secondSmallest = arr[i];
            }
        }

        return secondSmallest;
    }
}


