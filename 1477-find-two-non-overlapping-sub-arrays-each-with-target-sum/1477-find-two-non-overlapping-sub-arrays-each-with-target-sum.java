class Solution {
    public int minSumOfLengths(int[] arr, int target) {
        int n = arr.length;
        int INF = 1000000000;

        int[] dp = new int[n];
        int[] best = new int[n];

        for (int i = 0; i < n; i++) {
            dp[i] = INF;
        }

        int prefix = 0;
        int answer = INF;

        java.util.HashMap<Integer, Integer> map = new java.util.HashMap<>();
        map.put(0, -1);

        int bestLength = INF;

        for (int i = 0; i < n; i++) {
            prefix += arr[i];

            int start = map.getOrDefault(prefix - target, -2);

            if (start != -2) {
                int length = i - start;

                if (start >= 0 && best[start] != INF) {
                    answer = Math.min(answer, length + best[start]);
                }

                bestLength = Math.min(bestLength, length);
            }

            best[i] = bestLength;
            map.put(prefix, i);
        }

        return answer == INF ? -1 : answer;
    }
}
