class Solution {
    public int maxSubarraySumCircular(int[] nums) {
                int totalSum = 0;

        int currMax = 0;
        int maxSum = nums[0];

        int currMin = 0;
        int minSum = nums[0];

        for (int num : nums) {

            totalSum += num;

            // Kadane for maximum sum
            currMax = Math.max(num, currMax + num);
            maxSum = Math.max(maxSum, currMax);

            // Kadane for minimum sum
            currMin = Math.min(num, currMin + num);
            minSum = Math.min(minSum, currMin);
        }

        // all elements negative
        if (maxSum < 0) {
            return maxSum;
        }

        return Math.max(maxSum, totalSum - minSum);
        
    }
}