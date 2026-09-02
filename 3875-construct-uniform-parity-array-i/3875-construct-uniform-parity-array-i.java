class Solution {
    public boolean uniformArray(int[] nums1) {
        boolean hasEven = false;
        boolean hasOdd = false;

        for (int num : nums1) {
            if (num % 2 == 0) {
                hasEven = true;
            } else {
                hasOdd = true;
            }
        }

        // If all numbers already have the same parity
        if (!hasEven || !hasOdd) {
            return true;
        }

        // If both even and odd numbers exist,
        // an even number can be used to change an odd number's parity.
        return hasEven;
    }
}