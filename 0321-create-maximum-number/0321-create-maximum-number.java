class Solution {

    public int[] maxNumber(int[] nums1, int[] nums2, int k) {

        int n = nums1.length;
        int m = nums2.length;

        int[] result = new int[k];

        int start = Math.max(0, k - m);
        int end = Math.min(k, n);

        for (int i = start; i <= end; i++) {

            int[] a = maxSubsequence(nums1, i);
            int[] b = maxSubsequence(nums2, k - i);

            int[] candidate = merge(a, b);

            if (greater(candidate, 0, result, 0)) {
                result = candidate;
            }
        }

        return result;
    }

    // Get maximum subsequence of length k
    private int[] maxSubsequence(int[] nums, int k) {

        int n = nums.length;

        if (k == 0) {
            return new int[0];
        }

        int[] stack = new int[k];
        int top = 0;

        int remove = n - k;

        for (int num : nums) {

            while (top > 0 &&
                   remove > 0 &&
                   stack[top - 1] < num) {

                top--;
                remove--;
            }

            if (top < k) {
                stack[top++] = num;
            } else {
                remove--;
            }
        }

        return stack;
    }

    // Merge two subsequences into the largest possible number
    private int[] merge(int[] a, int[] b) {

        int[] result = new int[a.length + b.length];

        int i = 0;
        int j = 0;
        int r = 0;

        while (i < a.length || j < b.length) {

            if (greater(a, i, b, j)) {
                result[r++] = a[i++];
            } else {
                result[r++] = b[j++];
            }
        }

        return result;
    }

    // Compare a[i...] with b[j...]
    private boolean greater(int[] a, int i, int[] b, int j) {

        while (i < a.length && j < b.length) {

            if (a[i] > b[j]) {
                return true;
            }

            if (a[i] < b[j]) {
                return false;
            }

            i++;
            j++;
        }

        return j == b.length;
    }
}