class Solution {
    public int getLucky(String s, int k) {
        String s1 = "";

        for (int i = 0; i < s.length(); i++) {
            int val = s.charAt(i) - 'a' + 1;
            s1 += val;
        }

        int ans = 0;

        while (k > 0) {
            ans = sum(s1);
            s1 = String.valueOf(ans);
            k--;
        }

        return ans;
    }

    public int sum(String num) {
        int sum = 0;

        for (int i = 0; i < num.length(); i++) {
            sum += num.charAt(i) - '0';
        }

        return sum;
    }
}