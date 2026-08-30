class Solution {
    public int minimumDeletions(int[] nums) {

        int n = nums.length;

        int minIndex = 0;
        int maxIndex = 0;

        // Find minimum and maximum indices
        for (int i = 1; i < n; i++) {

            if (nums[i] < nums[minIndex]) {
                minIndex = i;
            }

            if (nums[i] > nums[maxIndex]) {
                maxIndex = i;
            }
        }

        // Make minIndex the smaller index
        int left = Math.min(minIndex, maxIndex);
        int right = Math.max(minIndex, maxIndex);

        // Option 1: Remove both from left
        int removeLeft = right + 1;

        // Option 2: Remove both from right
        int removeRight = n - left;

        // Option 3: Remove left element from left
        // and right element from right
        int removeBoth = (left + 1) + (n - right);

        return Math.min(removeLeft,
                Math.min(removeRight, removeBoth));
    }
}
