import java.util.*;

class Solution {
    public int uniqueXorTriplets(int[] nums) {
        int n = nums.length;
        if (n == 1) return 1;
        Set<Integer> pairXor = new HashSet<>();
        // Compute all unique XORs of two different elements
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                pairXor.add(nums[i] ^ nums[j]);
            }
        }
        BitSet seen = new BitSet();
        // Form triplets by XORing each pair XOR with every element
        for (int val : pairXor) {
            for (int num : nums) {
                seen.set(val ^ num);
            }
        }
        return seen.cardinality();
    }
}