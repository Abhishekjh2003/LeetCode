import java.util.*;

class Solution {

    static class Interval {
        int left;
        int right;
        int weight;
        int index;

        Interval(int left, int right, int weight, int index) {
            this.left = left;
            this.right = right;
            this.weight = weight;
            this.index = index;
        }
    }

    static class Node {
        long score;
        List<Integer> indices;

        Node(long score, List<Integer> indices) {
            this.score = score;
            this.indices = indices;
        }
    }

    public int[] maximumWeight(List<List<Integer>> intervals) {

        int n = intervals.size();

        List<Interval> arr = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            arr.add(new Interval(
                intervals.get(i).get(0),
                intervals.get(i).get(1),
                intervals.get(i).get(2),
                i
            ));
        }

        // Sort by starting position
        arr.sort((a, b) -> {
            if (a.left != b.left) {
                return Integer.compare(a.left, b.left);
            }
            return Integer.compare(a.right, b.right);
        });

        Node[][] dp = new Node[n + 1][5];

        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= 4; j++) {
                dp[i][j] = new Node(0, new ArrayList<>());
            }
        }

        for (int i = n - 1; i >= 0; i--) {

            Interval current = arr.get(i);

            // Don't take current interval
            for (int k = 1; k <= 4; k++) {

                Node skip = dp[i + 1][k];

                // Find first interval whose start > current.right
                int next = findNext(arr, i + 1, current.right);

                Node nextNode = dp[next][k - 1];

                List<Integer> selected =
                    new ArrayList<>(nextNode.indices);

                selected.add(current.index);
                Collections.sort(selected);

                Node take = new Node(
                    current.weight + nextNode.score,
                    selected
                );

                if (isBetter(take, skip)) {
                    dp[i][k] = take;
                } else {
                    dp[i][k] = skip;
                }
            }
        }

        List<Integer> answer = dp[0][4].indices;

        int[] result = new int[answer.size()];

        for (int i = 0; i < answer.size(); i++) {
            result[i] = answer.get(i);
        }

        return result;
    }

    private int findNext(
        List<Interval> arr,
        int start,
        int right
    ) {

        int low = start;
        int high = arr.size();

        while (low < high) {

            int mid = low + (high - low) / 2;

            if (arr.get(mid).left > right) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }

        return low;
    }

    private boolean isBetter(Node a, Node b) {

        if (a.score != b.score) {
            return a.score > b.score;
        }

        int size = Math.min(
            a.indices.size(),
            b.indices.size()
        );

        for (int i = 0; i < size; i++) {

            if (!a.indices.get(i).equals(b.indices.get(i))) {
                return a.indices.get(i) < b.indices.get(i);
            }
        }

        return a.indices.size() < b.indices.size();
    }
}