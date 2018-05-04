package gs.interview;

import java.util.Stack;

public class LongestValidParanthesisString {

    public static void main(String[] args){
        System.out.println(findLengthOfLongestValidParenthesisString(")()()"));
    }

    public static int findLengthOfLongestValidParenthesisString(String input){
        // Create a stack
        Stack<Integer> stack = new Stack<>();
        // push -1 as the initial index value to it
        stack.push(-1);
        char[] cArray = input.toCharArray();

        // initialize the length var
        int len = 0;
        for(int i = 0; i < cArray.length; i++){
            // if the character is left parenthesis then push the index onto the stack
            if(cArray[i] == '(')
                stack.push(i);
            // else if the character is right parenthesis
            else{
                // pop the previous opening bracket index
                stack.pop();
                // if the stack is not empty then check if the
                // len of substring formed with the current base
                // is greater than the current length
                if(!stack.isEmpty())
                    len = Math.max(len, i-stack.peek());
                // if the stack is empty then push the current index
                // to act as base for rest character to follow(if any)
                else
                    stack.push(i);
            }
        }
        return len;
    }
}
