class Solution {
    public int numberOfSets(int n, int k) {
        long MOD = 1000000007;
        int r = 2 * k;
        int N = n + k - 1;

        long result = 1;

        for (int i = 1; i <= r; i++) {
            result = result * (N - r + i) % MOD;
            result = result * modInverse(i, MOD) % MOD;
        }

        return (int) result;
    }

    private long modInverse(long a, long mod) {
        return power(a, mod - 2, mod);
    }

    private long power(long a, long b, long mod) {
        long result = 1;

        while (b > 0) {
            if ((b & 1) == 1) {
                result = result * a % mod;
            }

            a = a * a % mod;
            b >>= 1;
        }

        return result;
    }
}