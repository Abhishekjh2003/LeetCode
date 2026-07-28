class Solution {
    public int shipWithinDays(int[] weights, int days) {
        int low = 0;
        int high = 0;
        for (int weight : weights) {
            low = Math.max(low, weight);
            high += weight;
        }
        while (low < high) {
            int mid = low + (high - low) / 2;
            if (canShip(weights, days, mid)) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }
        return low;
    }
    private boolean canShip(int[] weights, int days, int capacity) {
        int requiredDays = 1;
        int currentLoad = 0;
        for (int weight : weights) {
            if (currentLoad + weight > capacity) {
                requiredDays++;
                currentLoad = weight;
            } else {
                currentLoad += weight;
            }
        }
        return requiredDays <= days;
    }
}