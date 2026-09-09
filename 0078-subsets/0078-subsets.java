import java.util.*;

class Solution {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> current = new ArrayList<>();

        backtrack(0, nums, current, result);

        return result;
    }

    private void backtrack(int index, int[] nums,
                           List<Integer> current,
                           List<List<Integer>> result) {

        if (index == nums.length) {
            result.add(new ArrayList<>(current));
            return;
        }

        // Include nums[index]
        current.add(nums[index]);
        backtrack(index + 1, nums, current, result);

        // Backtrack
        current.remove(current.size() - 1);

        // Don't include nums[index]
        backtrack(index + 1, nums, current, result);
    }
}