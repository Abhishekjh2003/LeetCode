class Solution {
    public int[] gcdValues(int[] nums, long[] queries) {
        int max = 0;
        for (int x : nums) {
            max = Math.max(max, x);
        }

        long[] freq = new long[max + 1];
        for (int x : nums) {
            freq[x]++;
        }

        long[] cnt = new long[max + 1];

        for (int g = 1; g <= max; g++) {
            long total = 0;

            for (int multiple = g; multiple <= max; multiple += g) {
                total += freq[multiple];
            }

            cnt[g] = total * (total - 1) / 2;
        }

        long[] exact = new long[max + 1];

        for (int g = max; g >= 1; g--) {
            exact[g] = cnt[g];

            for (int multiple = g * 2; multiple <= max; multiple += g) {
                exact[g] -= exact[multiple];
            }
        }

        long[] prefix = new long[max + 1];
        for (int g = 1; g <= max; g++) {
            prefix[g] = prefix[g - 1] + exact[g];
        }

        int[] ans = new int[queries.length];

        for (int i = 0; i < queries.length; i++) {
            long k = queries[i] + 1;

            int l = 1, r = max;

            while (l < r) {
                int mid = l + (r - l) / 2;

                if (prefix[mid] >= k) {
                    r = mid;
                } else {
                    l = mid + 1;
                }
            }

            ans[i] = l;
        }

        return ans;
    }
}