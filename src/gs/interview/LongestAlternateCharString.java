package gs.interview;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * Write a program which take a long string .Pick any two character and delete all
 * character except these two. In result iF we ±nd alternative sequence oF character
 * then that substring is valid. µind out the biggest length oF result and which two
 * character you select to get this result. Eg . Input String : abacdcd Output : 4
 * Explana³on: IF we take a and b in this string and delete all then result we get
 * aba .whose length is 3 . iF we select c and d and delete all From input string then
 * result we will get is cdcd which is valid string as it has c and d coming on
 * alternative order .So its length is 4 . Maximum oF 3 and 4 is 4 . So result is 4 .
 */
public class LongestAlternateCharString {
    public static void main(String[] args){
        String input = "abactryertvineetytrtytrtydcdcdcdc";
        System.out.println(findLongestAlternateCharString(input));
    }

    public static String findLongestAlternateCharString(String str){
        Set<Character> charSet = getUniqueCharsSet(str);
        int maxLen = 0;
        String maxLenStr = "";
        Set<String> combSet = getCombinationsSet(charSet);
        for(String comb: combSet) {
            String curStringToValidate = getStringToValidate(str, comb);
            if(isValidString(curStringToValidate) && maxLen < curStringToValidate.length()){
                maxLen = curStringToValidate.length();
                maxLenStr = curStringToValidate;
            }
        }
        return maxLenStr;
    }

    public static boolean isValidString(String input){
        boolean b1 = true;
        boolean b2 = true;
        if(input == null || input.isEmpty() || input.length() < 2) {
            return false;
        }
        char[] cArr = input.toCharArray();
        char c1 = cArr[0];
        char c2 = cArr[1];
        for(int i = 0; i < cArr.length; i+=2){
            if(cArr[i] != c1){
                b1 = false;
                break;
            }
            if((i + 1) <  cArr.length && cArr[i + 1] != c2){
                b2 = false;
                break;
            }
        }
        return b1 && b2;
    }

    public static String getStringToValidate(String str, String comb){
        char[] charArray = str.toCharArray();
        String res = "";
        for(Character c: charArray){
            if(comb.contains(c + ""))
                res +=  c;
        }
        return res;
    }

    public static Set<String> getCombinationsSet(Set<Character> charSet){
        Iterator<Character> i1 = charSet.iterator();
        Set<String> combSet = new HashSet<>();

        while(i1.hasNext()){
            char c1 = i1.next();
            Iterator<Character> i2 = charSet.iterator();
            while(i2.hasNext()){
                char c2 = i2.next();
                if(c1 != c2)
                    combSet.add(c1 + "" + c2);
            }
        }
        return combSet;
    }

    public static Set<Character> getUniqueCharsSet(String str){
        Set<Character> set = new HashSet<>();
        for(Character c: str.toCharArray())
            set.add(c);
        return set;
    }
}
