import java.util.*;

class Solution {
    public String minRemoveToMakeValid(String s) {

        StringBuilder str = new StringBuilder(s);
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < str.length(); i++) {

            char c = str.charAt(i);

            if (c == '(') {
                stack.push(i);
            }
            else if (c == ')') {

                if (!stack.isEmpty()) {
                    stack.pop();
                } else {
                    // Mark invalid ')'
                    str.setCharAt(i, '#');
                }
            }
        }

        // Remaining '(' are invalid
        while (!stack.isEmpty()) {
            int index = stack.pop();
            str.setCharAt(index, '#');
        }

        // Build answer without '#'
        StringBuilder ans = new StringBuilder();

        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) != '#') {
                ans.append(str.charAt(i));
            }
        }

        return ans.toString();
    }
}