import java.util.*;

class Solution {
    private static final int MOD = 1_000_000_007;
    private long[] sum, sq, lazy;
    private int n;

    public int sumCounts(int[] nums) {
        n = nums.length;
        sum = new long[4 * n];
        sq = new long[4 * n];
        lazy = new long[4 * n];

        Map<Integer, Integer> last = new HashMap<>();
        long ans = 0;

        for (int i = 0; i < n; i++) {
            int l = last.getOrDefault(nums[i], -1) + 1;

            update(1, 0, n - 1, l, i);

            ans = (ans + sq[1]) % MOD;
            last.put(nums[i], i);
        }

        return (int) ans;
    }

    private void apply(int node, int left, int right) {
        long len = right - left + 1L;

        sq[node] = (sq[node] + 2L * sum[node] + len) % MOD;
        sum[node] = (sum[node] + len) % MOD;
        lazy[node] = (lazy[node] + 1) % MOD;
    }

    private void push(int node, int left, int right) {
        if (lazy[node] == 0 || left == right) return;

        int mid = (left + right) / 2;
        applyLazy(node * 2, left, mid, lazy[node]);
        applyLazy(node * 2 + 1, mid + 1, right, lazy[node]);

        lazy[node] = 0;
    }

    private void applyLazy(int node, int left, int right, long val) {
        long len = right - left + 1L;

        sq[node] = (sq[node] + 2L * val % MOD * sum[node] % MOD
                    + len % MOD * val % MOD * val % MOD) % MOD;
        sum[node] = (sum[node] + len * val) % MOD;
        lazy[node] = (lazy[node] + val) % MOD;
    }

    private void update(int node, int left, int right, int ql, int qr) {
        if (ql > right || qr < left) return;

        if (ql <= left && right <= qr) {
            apply(node, left, right);
            return;
        }

        push(node, left, right);

        int mid = (left + right) / 2;
        update(node * 2, left, mid, ql, qr);
        update(node * 2 + 1, mid + 1, right, ql, qr);

        sum[node] = (sum[node * 2] + sum[node * 2 + 1]) % MOD;
        sq[node] = (sq[node * 2] + sq[node * 2 + 1]) % MOD;
    }
}