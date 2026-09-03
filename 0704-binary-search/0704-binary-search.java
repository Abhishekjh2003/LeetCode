class Solution {
    public int search(int[] nums, int target) {
        int low = 0;
        int hi = nums.length - 1;
        while (low <= hi) {
            int middle = low + (hi - low) / 2;
            if (nums[middle] == target) {
                return middle;
            } else if (nums[middle] < target) {
                low = middle + 1;
            } else {
                hi = middle - 1;
            }
        }
        return -1;
    }
}