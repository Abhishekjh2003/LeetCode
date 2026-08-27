class Solution {
    public String lexGreaterPermutation(String s, String target) {
        int n = s.length();
        int[] freq = new int[26];
        // Count characters in s
        for (char c : s.toCharArray()) {
            freq[c - 'a']++;
        }
        // Try to match target from left to right
        int i = 0;
        while (i < n) {
            int idx = target.charAt(i) - 'a';
            if (freq[idx] == 0) {
                break;
            }
            freq[idx]--;
            i++;
        }
        // Case 1: target itself cannot be continued.
        // Try to make the answer greater at position i.
        if (i < n) {
            int idx = target.charAt(i) - 'a';
            int greater = findGreater(freq, idx);
            if (greater != -1) {
                freq[greater]--;
                return build(target, i, greater, freq);
            }
        }
        // Case 2: Backtrack.
        // Restore characters from the matched prefix
        // and try making one position larger.
        for (int j = i - 1; j >= 0; j--) {
            int idx = target.charAt(j) - 'a';
            // Restore target[j]
            freq[idx]++;
            int greater = findGreater(freq, idx);
            if (greater != -1) {
                freq[greater]--;
                return build(target, j, greater, freq);
            }
        }
        return "";
    }
    // Find smallest character greater than target character
    private int findGreater(int[] freq, int target) {
        for (int i = target + 1; i < 26; i++) {
            if (freq[i] > 0) {
                return i;
            }
        }
        return -1;
    }
    // Build answer after choosing a greater character
    private String build(String target, int pos, int greater, int[] freq) {
        StringBuilder ans = new StringBuilder();
        // Prefix remains equal to target
        ans.append(target.substring(0, pos));
        // Make this position greater
        ans.append((char) ('a' + greater));
        // Fill remaining positions with smallest characters
        for (int i = 0; i < 26; i++) {
            while (freq[i] > 0) {
                ans.append((char) ('a' + i));
                freq[i]--;
            }
        }
        return ans.toString();
    }
}