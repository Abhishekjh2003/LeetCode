class Solution {
    public int longestSubsequence(int[] nums) {
        int n = nums.length;
        int xor = 0;
        boolean hasNonZero = false;

        for (int num : nums) {
            xor ^= num;

            if (num != 0) {
                hasNonZero = true;
            }
        }

        // Entire array has non-zero XOR
        if (xor != 0) {
            return n;
        }

        // All elements are zero
        if (!hasNonZero) {
            return 0;
        }

        // XOR is zero, but at least one element is non-zero.
        return n - 1;
    }
}