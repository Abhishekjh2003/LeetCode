class Solution {
    public int maxAbsoluteSum(int[] nums) {
        int maxEnding = 0, maxSum = 0;
        int minEnding = 0, minSum = 0;
        for (int num : nums) {
            maxEnding = Math.max(0, maxEnding + num);
            maxSum = Math.max(maxSum, maxEnding);
            minEnding = Math.min(0, minEnding + num);
            minSum = Math.min(minSum, minEnding);
        }
        return Math.max(maxSum, Math.abs(minSum));
    }
}
