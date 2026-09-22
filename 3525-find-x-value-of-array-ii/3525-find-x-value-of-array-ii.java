class Solution {

    static class Node {
        int product;
        int[] count;

        Node(int k) {
            count = new int[k];
            product = 1 % k;
        }
    }

    int k;
    Node[] tree;

    public int[] resultArray(int[] nums, int k, int[][] queries) {
        this.k = k;

        int n = nums.length;
        tree = new Node[4 * n];

        build(1, 0, n - 1, nums);

        int[] answer = new int[queries.length];

        for (int i = 0; i < queries.length; i++) {

            int index = queries[i][0];
            int value = queries[i][1];
            int start = queries[i][2];
            int x = queries[i][3];

            // Update nums[index]
            update(1, 0, n - 1, index, value % k);

            // Query from start to n - 1
            Node result = query(1, 0, n - 1, start, n - 1);

            answer[i] = result.count[x];
        }

        return answer;
    }

    void build(int node, int left, int right, int[] nums) {

        tree[node] = new Node(k);

        if (left == right) {
            int value = nums[left] % k;

            tree[node].product = value;
            tree[node].count[value] = 1;

            return;
        }

        int mid = (left + right) / 2;

        build(node * 2, left, mid, nums);
        build(node * 2 + 1, mid + 1, right, nums);

        tree[node] = merge(tree[node * 2], tree[node * 2 + 1]);
    }

    void update(int node, int left, int right, int index, int value) {

        if (left == right) {
            tree[node].product = value;

            tree[node].count = new int[k];
            tree[node].count[value] = 1;

            return;
        }

        int mid = (left + right) / 2;

        if (index <= mid) {
            update(node * 2, left, mid, index, value);
        } else {
            update(node * 2 + 1, mid + 1, right, index, value);
        }

        tree[node] = merge(tree[node * 2], tree[node * 2 + 1]);
    }

    Node query(int node, int left, int right, int ql, int qr) {

        if (ql <= left && right <= qr) {
            return tree[node];
        }

        int mid = (left + right) / 2;

        if (qr <= mid) {
            return query(node * 2, left, mid, ql, qr);
        }

        if (ql > mid) {
            return query(node * 2 + 1, mid + 1, right, ql, qr);
        }

        Node a = query(node * 2, left, mid, ql, qr);
        Node b = query(node * 2 + 1, mid + 1, right, ql, qr);

        return merge(a, b);
    }

    Node merge(Node a, Node b) {

        Node result = new Node(k);

        result.product =
            (int) ((long) a.product * b.product % k);

        // Subarrays starting in the left part
        for (int i = 0; i < k; i++) {
            result.count[i] = a.count[i];
        }

        // Subarrays that start in the right part
        for (int i = 0; i < k; i++) {

            int remainder =
                (int) ((long) a.product * i % k);

            result.count[remainder] += b.count[i];
        }

        return result;
    }
}