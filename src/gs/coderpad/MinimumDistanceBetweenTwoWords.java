package gs.coderpad;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MinimumDistanceBetweenTwoWords {public static void main(String[] args) {
    String str = "abcd";
    System.out.println(largestSubstringWithUniqueChars(str));
}

    public static String largestSubstringWithUniqueChars(String str){
        int largestLen = Integer.MIN_VALUE;
        int currLen = 0;
        String longestSubstring = "";
        String currWord = "";


        Map<Character, Integer> map = new HashMap<>();
        for(int i = 0; i < str.length();){
            if(!map.containsKey(str.charAt(i))){
                currWord += str.charAt(i);
                currLen++;
                i++;
            } else {
                if(currLen > largestLen || longestSubstring == ""){
                    largestLen = currLen;
                    longestSubstring = currWord;
                }
                currWord = "";
                currLen = 0;
                map.clear();
            }
        }
        return longestSubstring;
    }  }
