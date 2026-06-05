class Solution {
    String s;
    Long[][][][][] dpSum;
    Long[][][][][] dpCount;

    public long totalWaviness(long num1, long num2) {
        return solve(num2) - solve(num1 - 1);
    }

    private long solve(long n) {
        if (n < 100) return 0;

        s = String.valueOf(n);
        int len = s.length();

        dpSum = new Long[len][11][11][2][2];
        dpCount = new Long[len][11][11][2][2];

        return dfs(0, 10, 10, 1, 1)[0];
    }

    // returns {totalWaviness, countNumbers}
    private long[] dfs(int idx, int prev2, int prev1, int tight, int leadingZero) {
        if (idx == s.length()) {
            return new long[]{0, 1};
        }

        if (dpSum[idx][prev2][prev1][tight][leadingZero] != null) {
            return new long[]{
                dpSum[idx][prev2][prev1][tight][leadingZero],
                dpCount[idx][prev2][prev1][tight][leadingZero]
            };
        }

        int limit = tight == 1 ? s.charAt(idx) - '0' : 9;

        long totalWaviness = 0;
        long countNumbers = 0;

        for (int digit = 0; digit <= limit; digit++) {
            int newTight = (tight == 1 && digit == limit) ? 1 : 0;

            if (leadingZero == 1 && digit == 0) {
                long[] next = dfs(idx + 1, 10, 10, newTight, 1);
                totalWaviness += next[0];
                countNumbers += next[1];
            } else {
                long[] next = dfs(idx + 1, prev1, digit, newTight, 0);

                long add = 0;

                if (prev2 != 10) {
                    if ((prev1 > prev2 && prev1 > digit) ||
                        (prev1 < prev2 && prev1 < digit)) {
                        add = next[1];
                    }
                }

                totalWaviness += next[0] + add;
                countNumbers += next[1];
            }
        }

        dpSum[idx][prev2][prev1][tight][leadingZero] = totalWaviness;
        dpCount[idx][prev2][prev1][tight][leadingZero] = countNumbers;

        return new long[]{totalWaviness, countNumbers};
    }
}