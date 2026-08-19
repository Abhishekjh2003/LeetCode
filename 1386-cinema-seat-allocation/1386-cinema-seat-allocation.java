import java.util.*;

class Solution {
    public int maxNumberOfFamilies(int n, int[][] reservedSeats) {

        Map<Integer, Integer> map = new HashMap<>();

        // Store reserved seats using bitmask
        for (int[] seat : reservedSeats) {
            int row = seat[0];
            int col = seat[1];

            map.put(row, map.getOrDefault(row, 0) | (1 << (col - 1)));
        }

        int ans = 0;

        // Check rows having reserved seats
        for (int seats : map.values()) {

            // Seats 2-9 are completely free
            // Can place 2 families
            if ((seats & 0b0111111110) == 0) {
                ans += 2;
            }

            // Otherwise check whether at least one group can be placed
            else if ((seats & 0b0111100000) == 0 ||   // 2,3,4,5
                     (seats & 0b0001111000) == 0 ||   // 4,5,6,7
                     (seats & 0b0000011110) == 0) {   // 6,7,8,9

                ans += 1;
            }
        }

        // Rows with no reservations can fit 2 families
        ans += (n - map.size()) * 2;

        return ans;
    }
}
