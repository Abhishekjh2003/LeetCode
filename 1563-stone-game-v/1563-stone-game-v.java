class Solution {
    private int[][] dp;
    private int[] prefix;
    private int[] stones;

    public int stoneGameV(int[] stoneValue) {
        stones = stoneValue;
        int n = stoneValue.length;

        prefix = new int[n + 1];

        for (int i = 0; i < n; i++) {
            prefix[i + 1] = prefix[i] + stoneValue[i];
        }

        dp = new int[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                dp[i][j] = -1;
            }
        }

        return solve(0, n - 1);
    }

    private int solve(int left, int right) {
        if (left >= right) {
            return 0;
        }

        if (dp[left][right] != -1) {
            return dp[left][right];
        }

        int ans = 0;
        int leftSum = 0;

        int totalSum = prefix[right + 1] - prefix[left];
        int rightSum = totalSum;

        for (int k = left; k < right; k++) {

            leftSum += stones[k];
            rightSum -= stones[k];

            if (leftSum < rightSum) {
                ans = Math.max(
                    ans,
                    leftSum + solve(left, k)
                );
            }

            else if (leftSum > rightSum) {
                ans = Math.max(
                    ans,
                    rightSum + solve(k + 1, right)
                );
            }

            else {
                ans = Math.max(
                    ans,
                    Math.max(
                        leftSum + solve(left, k),
                        rightSum + solve(k + 1, right)
                    )
                );
            }
        }

        return dp[left][right] = ans;
    }
}