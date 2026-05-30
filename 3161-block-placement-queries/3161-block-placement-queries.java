import java.util.*;

class Solution {
    public List<Boolean> getResults(int[][] queries) {

        TreeSet<Integer> obstacles = new TreeSet<>();
        obstacles.add(0);

        int maxX = 0;
        for (int[] q : queries) {
            maxX = Math.max(maxX, q[1]);
        }

        SegmentTree seg = new SegmentTree(maxX + 1);

        List<Boolean> ans = new ArrayList<>();

        for (int[] q : queries) {

            if (q[0] == 1) {
                int x = q[1];

                Integer left = obstacles.floor(x);
                Integer right = obstacles.ceiling(x);

                if (right != null) {
                    seg.update(right, right - x);
                }

                seg.update(x, x - left);
                obstacles.add(x);
            } 
            else {
                int x = q[1];
                int size = q[2];

                Integer left = obstacles.floor(x);

                int maxGap = seg.query(0, x);
                int lastGap = x - left;

                ans.add(Math.max(maxGap, lastGap) >= size);
            }
        }

        return ans;
    }
}

class SegmentTree {
    int[] tree;
    int n;

    SegmentTree(int n) {
        this.n = n;
        tree = new int[4 * n];
    }

    void update(int index, int value) {
        update(1, 0, n - 1, index, value);
    }

    void update(int node, int start, int end, int index, int value) {
        if (start == end) {
            tree[node] = value;
            return;
        }

        int mid = start + (end - start) / 2;

        if (index <= mid) {
            update(2 * node, start, mid, index, value);
        } else {
            update(2 * node + 1, mid + 1, end, index, value);
        }

        tree[node] = Math.max(tree[2 * node], tree[2 * node + 1]);
    }

    int query(int left, int right) {
        return query(1, 0, n - 1, left, right);
    }

    int query(int node, int start, int end, int left, int right) {
        if (right < start || end < left) {
            return 0;
        }

        if (left <= start && end <= right) {
            return tree[node];
        }

        int mid = start + (end - start) / 2;

        return Math.max(
            query(2 * node, start, mid, left, right),
            query(2 * node + 1, mid + 1, end, left, right)
        );
    }
}