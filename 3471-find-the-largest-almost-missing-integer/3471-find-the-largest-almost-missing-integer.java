class Solution {
    public int largestInteger(int[] nums, int k) {
        int n = nums.length;
        int[] count = new int[51];

        for (int i = 0; i <= n - k; i++) {
            boolean[] seen = new boolean[51];

            for (int j = i; j < i + k; j++) {
                if (!seen[nums[j]]) {
                    count[nums[j]]++;
                    seen[nums[j]] = true;
                }
            }
        }

        // Include 0
        for (int num = 50; num >= 0; num--) {
            if (count[num] == 1) {
                return num;
            }
        }

        return -1;
    }
}