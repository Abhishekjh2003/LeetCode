class Solution {
    public String maxSumOfSquares(int num, int sum) {
        if ((long) num * 9 < sum) {
            return "";
        }

        StringBuilder ans = new StringBuilder(num);

        // Place as many 9s as possible
        while (sum >= 9) {
            ans.append('9');
            sum -= 9;
        }

        // Place the remaining digit
        if (sum > 0) {
            ans.append((char) ('0' + sum));
        }

        // Fill remaining positions with zeros
        while (ans.length() < num) {
            ans.append('0');
        }

        return ans.toString();
    }
}