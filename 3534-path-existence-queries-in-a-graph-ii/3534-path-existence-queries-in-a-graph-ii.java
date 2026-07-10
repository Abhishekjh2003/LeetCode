class Solution {
    public int[] pathExistenceQueries(int n, int[] nums, int maxDiff, int[][] queries) {
        Integer[] idx = new Integer[n];
        for (int i = 0; i < n; i++) idx[i] = i;

        Arrays.sort(idx, (a, b) -> Integer.compare(nums[a], nums[b]));

        int[] pos = new int[n];
        for (int i = 0; i < n; i++) {
            pos[idx[i]] = i;
        }

        int[] reach = new int[n];
        int j = 0;

        for (int i = 0; i < n; i++) {
            while (j + 1 < n &&
                   nums[idx[j + 1]] - nums[idx[i]] <= maxDiff) {
                j++;
            }
            reach[i] = j;
        }

        int LOG = 18;
        int[][] up = new int[LOG][n];

        for (int i = 0; i < n; i++) {
            up[0][i] = reach[i];
        }

        for (int k = 1; k < LOG; k++) {
            for (int i = 0; i < n; i++) {
                up[k][i] = up[k - 1][up[k - 1][i]];
            }
        }

        int[] ans = new int[queries.length];

        for (int qi = 0; qi < queries.length; qi++) {
            int u = pos[queries[qi][0]];
            int v = pos[queries[qi][1]];

            if (u > v) {
                int t = u;
                u = v;
                v = t;
            }

            if (u == v) {
                ans[qi] = 0;
                continue;
            }

            if (reach[u] < v) {
                int cur = u;
                int jumps = 0;

                for (int k = LOG - 1; k >= 0; k--) {
                    if (up[k][cur] < v) {
                        jumps += 1 << k;
                        cur = up[k][cur];
                    }
                }

                cur = up[0][cur];
                jumps++;

                ans[qi] = (cur >= v) ? jumps : -1;
            } else {
                ans[qi] = 1;
            }
        }

        return ans;
    }
}