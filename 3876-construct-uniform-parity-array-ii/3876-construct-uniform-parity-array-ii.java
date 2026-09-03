class Solution {
    public boolean uniformArray(int[] nums1) {
        int minOdd = Integer.MAX_VALUE;
        int minEven = Integer.MAX_VALUE;
        int countOdd = 0;
        int countEven = 0;

        for (int num : nums1) {
            if (num % 2 != 0) {
                countOdd++;
                minOdd = Math.min(minOdd, num);
            } else {
                countEven++;
                minEven = Math.min(minEven, num);
            }
        }

        // Target 1: Make all EVEN
        // Possible if there are no odd numbers to begin with (the smallest odd can never be made even)
        if (countOdd == 0) {
            return true;
        }

        // Target 2: Make all ODD
        // Possible if there are no even numbers, or if the smallest odd number is smaller than all even numbers
        if (countEven == 0 || minOdd < minEven) {
            return true;
        }

        return false;
    }
}