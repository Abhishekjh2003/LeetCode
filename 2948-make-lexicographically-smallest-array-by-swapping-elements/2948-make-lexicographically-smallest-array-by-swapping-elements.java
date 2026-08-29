import java.util.*;

class Solution {
    public int[] lexicographicallySmallestArray(int[] nums, int limit) {

        int n = nums.length;

        // Store value and original index
        int[][] arr = new int[n][2];

        for (int i = 0; i < n; i++) {
            arr[i][0] = nums[i];
            arr[i][1] = i;
        }

        // Sort by value
        Arrays.sort(arr, (a, b) -> Integer.compare(a[0], b[0]));

        int[] ans = new int[n];

        int i = 0;

        while (i < n) {

            int j = i;

            // Find one connected group
            while (j + 1 < n &&
                   arr[j + 1][0] - arr[j][0] <= limit) {
                j++;
            }

            // Collect original indices
            List<Integer> indices = new ArrayList<>();

            for (int k = i; k <= j; k++) {
                indices.add(arr[k][1]);
            }

            // Sort original indices
            Collections.sort(indices);

            // Values are already sorted
            int p = 0;

            for (int k = i; k <= j; k++) {
                ans[indices.get(p++)] = arr[k][0];
            }

            i = j + 1;
        }

        return ans;
    }
}