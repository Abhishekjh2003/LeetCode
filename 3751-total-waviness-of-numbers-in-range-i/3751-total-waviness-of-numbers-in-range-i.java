class Solution {
    public int totalWaviness(int num1, int num2) {
         int res = 0;
        for (int num = num1; num <= num2; num++) {
            res += getWaviness(num);
        }
        return res;
    }
    private int getWaviness(int num) {
        String str = String.valueOf(num);
        int count = 0;
        for (int i = 1; i < str.length() - 1; i++) {
            int prev = str.charAt(i - 1) - '0';
            int curr = str.charAt(i) - '0';
            int next = str.charAt(i + 1) - '0';

            if (curr > prev && curr > next) {
                count++;
            } else if (curr < prev && curr < next) {
                count++;
            }
        }
        return count;
    }
}