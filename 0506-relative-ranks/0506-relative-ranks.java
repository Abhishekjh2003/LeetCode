import java.util.*;

class Solution {
    public String[] findRelativeRanks(int[] score) {

        int n = score.length;

        String[] result = new String[n];

        int[] copy = score.clone();

        Arrays.sort(copy);

        HashMap<Integer, String> map = new HashMap<>();

        for(int i = n - 1; i >= 0; i--) {

            if(i == n - 1) {
                map.put(copy[i], "Gold Medal");
            }
            else if(i == n - 2) {
                map.put(copy[i], "Silver Medal");
            }
            else if(i == n - 3) {
                map.put(copy[i], "Bronze Medal");
            }
            else {
                map.put(copy[i], String.valueOf(n - i));
            }
        }

        for(int i = 0; i < n; i++) {
            result[i] = map.get(score[i]);
        }

        return result;
    }
}