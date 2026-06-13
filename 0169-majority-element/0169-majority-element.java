class Solution {
    public int majorityElement(int[] nums) {
        int count = 0;
        int candidate = 0;
        for (int num : nums) {
            // If count reaches 0, we pick a new candidate
            if (count == 0) {
                candidate = num;
            }      
            // If the current number matches the candidate, increment count.
            // Otherwise, decrement count.
            if (num == candidate) {
                count++;
            } else {
                count--;
            }
        }
        return candidate;
    }
}