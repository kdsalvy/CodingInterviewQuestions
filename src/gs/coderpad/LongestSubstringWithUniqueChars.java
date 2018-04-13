package gs.coderpad;

import java.util.HashSet;
import java.util.Set;

public class LongestSubstringWithUniqueChars {
    public static void main(String[] args) {
        String str = "aabcd";
        System.out.println(largestSubstringWithUniqueChars(str));
    }

    public static String largestSubstringWithUniqueChars(String str){
        int start = 0;
        int max = 0;

        char[] cArray = str.toCharArray();
        Set<Character> cSet = new HashSet<>();
        int i = 0;
        while(i < cArray.length){
            int len = 0;
            int index = i;

            while( i < cArray.length && !cSet.contains(cArray[i])){
                cSet.add(cArray[i]);
                len++;
                i++;
            }

            if(max < len){
                max = len;
                start = index;
            }

            cSet.clear();
        }

        return str.substring(start, start + max);
    }
}
