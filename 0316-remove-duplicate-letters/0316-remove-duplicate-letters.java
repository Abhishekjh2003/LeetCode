class Solution {
    public String removeDuplicateLetters(String s) {

        int[] freq = new int[26];
        boolean[] used = new boolean[26];

        // Count frequency of each character
        for (char c : s.toCharArray()) {
            freq[c - 'a']++;
        }

        StringBuilder stack = new StringBuilder();

        for (char c : s.toCharArray()) {

            freq[c - 'a']--;

            // Already included
            if (used[c - 'a']) {
                continue;
            }

            // Remove larger characters if they appear again later
            while (stack.length() > 0
                    && stack.charAt(stack.length() - 1) > c
                    && freq[stack.charAt(stack.length() - 1) - 'a'] > 0) {

                char removed = stack.charAt(stack.length() - 1);
                stack.deleteCharAt(stack.length() - 1);
                used[removed - 'a'] = false;
            }

            stack.append(c);
            used[c - 'a'] = true;
        }

        return stack.toString();
    }
}