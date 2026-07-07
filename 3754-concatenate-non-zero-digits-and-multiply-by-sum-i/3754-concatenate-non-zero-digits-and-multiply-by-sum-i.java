class Solution {
    public long sumAndMultiply(int n) {
        long num = 0;
        long place = 1;
        int sum = 0;

        while (n > 0) {
            int digit = n % 10;

            if (digit != 0) {
                num += (long) digit * place;
                sum += digit;
                place *= 10;
            }

            n /= 10;
        }

        return num * sum;
    }
}