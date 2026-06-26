class Solution {
    public long countMajoritySubarrays(int[] nums, int target) {
        int n = nums.length;
        Fenwick bit = new Fenwick(2 * n + 5);

        int offset = n + 2;
        int prefix = 0;
        long ans = 0;

        // prefix sum 0 before starting
        bit.add(prefix + offset, 1);

        for (int x : nums) {
            if (x == target) {
                prefix += 1;
            } else {
                prefix -= 1;
            }

            // count previous prefix sums < current prefix
            ans += bit.query(prefix + offset - 1);

            bit.add(prefix + offset, 1);
        }

        return ans;
    }

    static class Fenwick {
        long[] tree;

        Fenwick(int n) {
            tree = new long[n + 1];
        }

        void add(int index, long value) {
            while (index < tree.length) {
                tree[index] += value;
                index += index & -index;
            }
        }

        long query(int index) {
            long sum = 0;
            while (index > 0) {
                sum += tree[index];
                index -= index & -index;
            }
            return sum;
        }
    }
}