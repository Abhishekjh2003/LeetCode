import java.util.*;

class Solution {
    public int maximumLength(int[] nums) {
        Map<Long, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put((long) num, map.getOrDefault((long) num, 0) + 1);
        }
        int ans = 1;
        for (long num : map.keySet()) {
            if (num == 1) continue;
            long curr = num;
            int len = 0;
            while (map.containsKey(curr) && map.get(curr) >= 2) {
                len += 2;
                curr = curr * curr;
            }
            if (map.containsKey(curr)) {
                len += 1;
            } else {
                len -= 1;
            }
            ans = Math.max(ans, len);
        }
        int ones = map.getOrDefault(1L, 0);
        if (ones > 0) {
            if (ones % 2 == 0) ones--;
            ans = Math.max(ans, ones);
        }
        return ans;
    }
}