class Solution {
    public String shortestBeautifulSubstring(String s, int k) {

        int n = s.length();
        int left = 0;
        int count = 0;

        String ans = "";

        for (int right = 0; right < n; right++) {

            if (s.charAt(right) == '1') {
                count++;
            }

            // We have exactly k ones
            while (count == k) {

                // Current substring starts and ends with 1
                if (s.charAt(left) == '1') {

                    String current = s.substring(left, right + 1);

                    if (ans.equals("")
                            || current.length() < ans.length()
                            || (current.length() == ans.length()
                                && current.compareTo(ans) < 0)) {
                        ans = current;
                    }

                    count--;
                    left++;
                } else {
                    left++;
                }
            }
        }

        return ans;
    }
}