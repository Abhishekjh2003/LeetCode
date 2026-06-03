import java.util.*;
class Solution {
    public int earliestFinishTime(int[] landStartTime, int[] landDuration,
                                  int[] waterStartTime, int[] waterDuration) {
        return Math.min(
            solve(landStartTime, landDuration, waterStartTime, waterDuration),
            solve(waterStartTime, waterDuration, landStartTime, landDuration)
        );
    }
    private int solve(int[] start1, int[] dur1, int[] start2, int[] dur2) {
        int m = start2.length;
        int[][] rides = new int[m][2];
        for (int i = 0; i < m; i++) {
            rides[i][0] = start2[i];
            rides[i][1] = dur2[i];
        }
        Arrays.sort(rides, (a, b) -> Integer.compare(a[0], b[0]));
        int[] prefixMinDuration = new int[m];
        int[] suffixMinFinish = new int[m];

        prefixMinDuration[0] = rides[0][1];
        for (int i = 1; i < m; i++) {
            prefixMinDuration[i] = Math.min(prefixMinDuration[i - 1], rides[i][1]);
        }
        suffixMinFinish[m - 1] = rides[m - 1][0] + rides[m - 1][1];
        for (int i = m - 2; i >= 0; i--) {
            suffixMinFinish[i] = Math.min(
                suffixMinFinish[i + 1],
                rides[i][0] + rides[i][1]
            );
        }
        int ans = Integer.MAX_VALUE;
        for (int i = 0; i < start1.length; i++) {
            int firstEnd = start1[i] + dur1[i];
            int idx = lowerBound(rides, firstEnd);

            if (idx < m) {
                ans = Math.min(ans, suffixMinFinish[idx]);
            }

            if (idx > 0) {
                ans = Math.min(ans, firstEnd + prefixMinDuration[idx - 1]);
            }
        }
        return ans;
    }
    private int lowerBound(int[][] rides, int target) {
        int low = 0, high = rides.length;

        while (low < high) {
            int mid = low + (high - low) / 2;

            if (rides[mid][0] >= target) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }
        return low;
    }
}