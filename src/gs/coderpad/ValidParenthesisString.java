package gs.coderpad;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class ValidParenthesisString {

    public static void main(String[] args){
        String str = "(()(()))";
        System.out.println(isValidString(str));
    }

    public static boolean isValidString(String str){
        boolean result = false;
        Stack<Character> stack = new Stack<>();
        Map<Character, Character> charMap = new HashMap<>();
        charMap.put('(', ')');
        charMap.put('{','}');
        charMap.put('[',']');

        int maxLen = 0;
        for(int i = 0; i < str.length(); i++){
            char cur = str.charAt(i);
            if(charMap.keySet().contains(cur))
                stack.push(cur);
            else{
                if(stack.isEmpty())
                    return false;
                if(charMap.values().contains(cur) && charMap.get(stack.peek()) == cur)
                    stack.pop();
                else
                    return false;
            }
        }
        if(stack.isEmpty())
            return true;
        else
            return false;
    }

}
