import java.util.HashSet;
class Solution {
    public int missingMultiple(int[] nums, int k) {  
        HashSet<Integer> set = new HashSet<>();
        for (int num : nums) {
            set.add(num);
        }
        int mult = k;
        while (set.contains(mult)) {
            mult += k;
        }
        return mult;
    }
}
