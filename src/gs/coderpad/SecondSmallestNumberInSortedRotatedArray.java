package gs.coderpad;

public class SecondSmallestNumberInSortedRotatedArray {

    public static void main(String[] args){
//        int arr[] =  {5, 6, 1, 2, 3, 4};
//        int arr[] =  {1, 2, 3, 4};
//        int arr[] =  {1};
//        int arr[] =  {1, 2};
//        int arr[] =  {2, 1};
//        int arr[] =  {5, 6, 7, 1, 2, 3, 4};
//        int arr[] =  {1, 2, 3, 4, 5, 6, 7};
//        int arr[] =  {2, 3, 4, 5, 6, 7, 8, 1};
        int arr[] =  {3, 4, 5, 1, 2};
        System.out.println(findSecondSmallestNumber(arr));
    }

    /**
     * Finds second smallest number in a sorted rotated array
     * @param arr
     * @return
     */
    public static int findSecondSmallestNumber(int[] arr){
        if(arr.length == 1)
            return 0;
        int index = -1;
        for(int i = 1; i < arr.length; i++){
            if(arr[i - 1] > arr [i])
                index = i + 1;
        }
        if(index == -1)
            index = 1;
        if(index == arr.length)
            index = 0;
        return arr[index];
    }
}
