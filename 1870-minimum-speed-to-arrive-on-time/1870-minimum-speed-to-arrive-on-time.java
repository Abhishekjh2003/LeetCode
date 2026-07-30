class Solution {
    public int minSpeedOnTime(int[] dist, double hour) {
        int n = dist.length;

        // Impossible to reach on time
        if (hour <= n - 1) {
            return -1;
        }

        int low = 1;
        int high = 10000000;
        int ans = -1;

        while (low <= high) {
            int mid = low + (high - low) / 2;

            if (canReach(dist, hour, mid)) {
                ans = mid;
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }

        return ans;
    }

    private boolean canReach(int[] dist, double hour, int speed) {
        double time = 0.0;

        for (int i = 0; i < dist.length - 1; i++) {
            time += Math.ceil((double) dist[i] / speed);
        }

        time += (double) dist[dist.length - 1] / speed;

        return time <= hour;
    }
}