import java.util.*;

public class Solution {
    public String lexPalindromicPermutation(String s, String target) {
        int n = s.length();
        int[] freq = new int[26];
        for (char c : s.toCharArray()) {
            freq[c - 'a']++;
        }

        // Validate if palindrome can be formed
        int oddCount = 0;
        char midChar = 0;
        for (int i = 0; i < 26; i++) {
            if (freq[i] % 2 != 0) {
                oddCount++;
                midChar = (char) ('a' + i);
            }
        }

        if (oddCount > 1) {
            return "";
        }

        int m = n / 2;
        int[] halfFreq = new int[26];
        for (int i = 0; i < 26; i++) {
            halfFreq[i] = freq[i] / 2;
        }

        // Try exact match on prefix of length m first
        String exactMatch = tryPrefix(target, m, halfFreq, midChar, n);
        if (exactMatch != null && exactMatch.compareTo(target) > 0) {
            return exactMatch;
        }

        // Try branching at position i (from m - 1 down to 0)
        for (int i = m - 1; i >= 0; i--) {
            int[] currentFreq = halfFreq.clone();
            boolean possible = true;
            
            for (int k = 0; k < i; k++) {
                int c = target.charAt(k) - 'a';
                if (currentFreq[c] > 0) {
                    currentFreq[c]--;
                } else {
                    possible = false;
                    break;
                }
            }

            if (!possible) continue;

            int targetChar = target.charAt(i) - 'a';
            for (int nextChar = targetChar + 1; nextChar < 26; nextChar++) {
                if (currentFreq[nextChar] > 0) {
                    currentFreq[nextChar]--;

                    // Build answer
                    StringBuilder half = new StringBuilder();
                    half.append(target, 0, i);
                    half.append((char) ('a' + nextChar));

                    for (int c = 0; c < 26; c++) {
                        while (currentFreq[c] > 0) {
                            half.append((char) ('a' + c));
                            currentFreq[c]--;
                        }
                    }

                    StringBuilder full = new StringBuilder(half);
                    if (n % 2 != 0) {
                        full.append(midChar);
                    }
                    full.append(new StringBuilder(half).reverse());
                    return full.toString();
                }
            }
        }

        return "";
    }

    private String tryPrefix(String target, int m, int[] halfFreq, char midChar, int n) {
        int[] currentFreq = halfFreq.clone();
        StringBuilder half = new StringBuilder();

        for (int i = 0; i < m; i++) {
            int c = target.charAt(i) - 'a';
            if (currentFreq[c] > 0) {
                currentFreq[c]--;
                half.append(target.charAt(i));
            } else {
                return null;
            }
        }

        StringBuilder full = new StringBuilder(half);
        if (n % 2 != 0) {
            full.append(midChar);
        }
        full.append(new StringBuilder(half).reverse());
        return full.toString();
    }
}