import java.util.*;

class Solution {
    public int maxBuilding(int n, int[][] restrictions) {
        Arrays.sort(restrictions, (a, b) -> a[0] - b[0]);

        int m = restrictions.length;

        // Add building 1 restriction: height = 0
        int[][] arr = new int[m + 1][2];
        arr[0][0] = 1;
        arr[0][1] = 0;

        for (int i = 0; i < m; i++) {
            arr[i + 1] = restrictions[i];
        }

        // Left to right
        for (int i = 1; i <= m; i++) {
            int dist = arr[i][0] - arr[i - 1][0];
            arr[i][1] = Math.min(arr[i][1], arr[i - 1][1] + dist);
        }

        // Right to left
        for (int i = m - 1; i >= 0; i--) {
            int dist = arr[i + 1][0] - arr[i][0];
            arr[i][1] = Math.min(arr[i][1], arr[i + 1][1] + dist);
        }

        int ans = 0;

        // Check between restrictions
        for (int i = 1; i <= m; i++) {
            int x1 = arr[i - 1][0];
            int h1 = arr[i - 1][1];
            int x2 = arr[i][0];
            int h2 = arr[i][1];

            int dist = x2 - x1;

            int maxHeight = (h1 + h2 + dist) / 2;
            ans = Math.max(ans, maxHeight);
        }

        // Check after last restricted building up to n
        int lastPos = arr[m][0];
        int lastHeight = arr[m][1];

        ans = Math.max(ans, lastHeight + (n - lastPos));

        return ans;
    }
}