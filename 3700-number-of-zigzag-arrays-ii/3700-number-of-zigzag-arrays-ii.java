class Solution {
    static final long MOD = 1_000_000_007L;

    public int zigZagArrays(int n, int l, int r) {
        int m = r - l + 1;
        int size = 2 * m;

        long[][] T = new long[size][size];

        for (int x = 0; x < m; x++) {
            int down = x;
            int up = x + m;

            for (int y = x + 1; y < m; y++) {
                T[y][up] = 1;
            }

            for (int y = 0; y < x; y++) {
                T[y + m][down] = 1;
            }
        }

        long[] start = new long[size];
        for (int i = 0; i < size; i++) {
            start[i] = 1;
        }

        long[][] power = matrixPower(T, n - 1);
        long[] ansVec = multiply(power, start);

        long ans = 0;
        for (long val : ansVec) {
            ans = (ans + val) % MOD;
        }

        return (int) ans;
    }

    private long[][] matrixPower(long[][] base, long exp) {
        int n = base.length;
        long[][] res = new long[n][n];

        for (int i = 0; i < n; i++) {
            res[i][i] = 1;
        }

        while (exp > 0) {
            if ((exp & 1) == 1) {
                res = multiply(res, base);
            }

            base = multiply(base, base);
            exp >>= 1;
        }

        return res;
    }

    private long[][] multiply(long[][] a, long[][] b) {
        int n = a.length;
        long[][] c = new long[n][n];

        for (int i = 0; i < n; i++) {
            for (int k = 0; k < n; k++) {
                if (a[i][k] == 0) continue;

                for (int j = 0; j < n; j++) {
                    if (b[k][j] == 0) continue;

                    c[i][j] = (c[i][j] + a[i][k] * b[k][j]) % MOD;
                }
            }
        }

        return c;
    }

    private long[] multiply(long[][] a, long[] v) {
        int n = a.length;
        long[] res = new long[n];

        for (int i = 0; i < n; i++) {
            long sum = 0;

            for (int j = 0; j < n; j++) {
                if (a[i][j] == 0) continue;

                sum = (sum + a[i][j] * v[j]) % MOD;
            }

            res[i] = sum;
        }

        return res;
    }
}