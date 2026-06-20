import java.util.*;

class Solution {
    public long maximumSubarraySum(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        long sum = 0;
        long max = 0;

        int left = 0;

        for (int right = 0; right < nums.length; right++) {
            sum += nums[right];
            map.put(nums[right], map.getOrDefault(nums[right], 0) + 1);

            if (right - left + 1 > k) {
                sum -= nums[left];
                map.put(nums[left], map.get(nums[left]) - 1);

                if (map.get(nums[left]) == 0) {
                    map.remove(nums[left]);
                }

                left++;
            }

            if (right - left + 1 == k && map.size() == k) {
                max = Math.max(max, sum);
            }
        }

        return max;
    }
}