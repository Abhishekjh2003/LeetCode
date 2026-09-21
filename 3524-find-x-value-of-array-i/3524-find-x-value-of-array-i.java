class Solution {
    public long[] resultArray(int[] nums, int k) {
        long[] ans = new long[k];
        long[] dp = new long[k];
        for (int num : nums) {
            int value = num % k;
            long[] next = new long[k];
            // Start a new subarray
            next[value]++;
            // Extend previous subarrays
            for (int r = 0; r < k; r++) {
                if (dp[r] > 0) {
                    int product = (r * value) % k;
                    next[product] += dp[r];
                }
            }
            dp = next;
            // Add counts of subarrays ending here
            for (int r = 0; r < k; r++) {
                ans[r] += dp[r];
            }
        }
        return ans;
    }
}