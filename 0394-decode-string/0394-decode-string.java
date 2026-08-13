import java.util.*;

class Solution {
    public String decodeString(String s) {
        Stack<Integer> countStack = new Stack<>();
        Stack<String> stringStack = new Stack<>();

        StringBuilder current = new StringBuilder();
        int count = 0;

        for (char ch : s.toCharArray()) {

            if (Character.isDigit(ch)) {
                count = count * 10 + (ch - '0');
            }

            else if (ch == '[') {
                countStack.push(count);
                stringStack.push(current.toString());

                count = 0;
                current = new StringBuilder();
            }

            else if (ch == ']') {
                int repeat = countStack.pop();
                String previous = stringStack.pop();

                StringBuilder temp = new StringBuilder(previous);

                for (int i = 0; i < repeat; i++) {
                    temp.append(current);
                }

                current = temp;
            }

            else {
                current.append(ch);
            }
        }

        return current.toString();
    }
}