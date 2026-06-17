class Solution {
    public char processStr(String s, long k) {
        long len = 0;

        // Calculate final length
        for (char ch : s.toCharArray()) {
            if (ch == '*') {
                len = Math.max(0, len - 1);
            } else if (ch == '#') {
                len *= 2;
            } else if (ch == '%') {
                // length unchanged
            } else {
                len++;
            }
        }

        if (k >= len) {
            return '.';
        }

        // Work backwards
        for (int i = s.length() - 1; i >= 0; i--) {
            char ch = s.charAt(i);

            if (ch == '*') {
                len++;
            } 
            else if (ch == '#') {
                len /= 2;
                if (k >= len) {
                    k -= len;
                }
            } 
            else if (ch == '%') {
                k = len - 1 - k;
            } 
            else {
                len--;
                if (k == len) {
                    return ch;
                }
            }
        }

        return '.';
    }
}