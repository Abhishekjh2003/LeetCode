class Solution {
    public int[] validSequence(String word1, String word2) {
        int n = word1.length();
        int m = word2.length();

        // suf[i] = earliest position in word1 from which
        // word2[i...] can be matched exactly.
        int[] suf = new int[m + 1];

        suf[m] = n;

        int p = n - 1;

        for (int i = m - 1; i >= 0; i--) {
            while (p >= 0 && word1.charAt(p) != word2.charAt(i)) {
                p--;
            }

            if (p < 0) {
                suf[i] = -1;
            } else {
                suf[i] = p;
                p--;
            }
        }

        int[] ans = new int[m];

        int pos = 0;
        boolean usedMismatch = false;

        for (int i = 0; i < m; i++) {

            while (pos < n) {

                // Case 1: Exact match
                if (word1.charAt(pos) == word2.charAt(i)) {
                    ans[i] = pos;
                    pos++;
                    break;
                }

                // Case 2: Use the one allowed mismatch
                if (!usedMismatch) {
                    /*
                     * After changing word1[pos] to word2[i],
                     * the remaining word2[i+1...] must be
                     * exactly matchable after pos.
                     */
                    if (suf[i + 1] > pos) {
                        ans[i] = pos;
                        pos++;
                        usedMismatch = true;
                        break;
                    }
                }

                pos++;
            }

            // Couldn't construct a valid sequence
            if (ans[i] == 0 && i != 0) {
                return new int[0];
            }

            if (pos > n) {
                return new int[0];
            }
        }

        return ans;
    }
}