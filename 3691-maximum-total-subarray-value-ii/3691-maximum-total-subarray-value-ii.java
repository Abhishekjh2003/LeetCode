import java.util.*;

class Solution {
    public long maxTotalValue(int[] nums, int k) {
        int n = nums.length;
        long totalCount = 1L * n * (n + 1) / 2;
        long totalSum = totalRangeSum(nums);

        int mn = Integer.MAX_VALUE, mx = Integer.MIN_VALUE;
        for (int x : nums) {
            mn = Math.min(mn, x);
            mx = Math.max(mx, x);
        }

        int low = 0, high = mx - mn;

        while (low < high) {
            int mid = low + (high - low + 1) / 2;
            Pair less = countAndSumLE(nums, mid - 1);
            long countGE = totalCount - less.count;

            if (countGE >= k) low = mid;
            else high = mid - 1;
        }

        int threshold = low;

        Pair lessOrEqual = countAndSumLE(nums, threshold);
        long countGreater = totalCount - lessOrEqual.count;
        long sumGreater = totalSum - lessOrEqual.sum;

        return sumGreater + (k - countGreater) * 1L * threshold;
    }

    static class Pair {
        long count, sum;
        Pair(long c, long s) {
            count = c;
            sum = s;
        }
    }

    private Pair countAndSumLE(int[] nums, int limit) {
        if (limit < 0) return new Pair(0, 0);

        Deque<long[]> maxQ = new ArrayDeque<>();
        Deque<long[]> minQ = new ArrayDeque<>();

        long count = 0, sum = 0;
        long sumMax = 0, sumMin = 0;
        int left = 0;

        for (int right = 0; right < nums.length; right++) {
            int x = nums[right];

            long c = 1;
            while (!maxQ.isEmpty() && maxQ.peekLast()[0] <= x) {
                long[] last = maxQ.pollLast();
                sumMax -= last[0] * last[1];
                c += last[1];
            }
            maxQ.addLast(new long[]{x, c});
            sumMax += 1L * x * c;

            c = 1;
            while (!minQ.isEmpty() && minQ.peekLast()[0] >= x) {
                long[] last = minQ.pollLast();
                sumMin -= last[0] * last[1];
                c += last[1];
            }
            minQ.addLast(new long[]{x, c});
            sumMin += 1L * x * c;

            while (maxQ.peekFirst()[0] - minQ.peekFirst()[0] > limit) {
                long[] a = maxQ.peekFirst();
                sumMax -= a[0];
                a[1]--;
                if (a[1] == 0) maxQ.pollFirst();

                long[] b = minQ.peekFirst();
                sumMin -= b[0];
                b[1]--;
                if (b[1] == 0) minQ.pollFirst();

                left++;
            }

            count += right - left + 1;
            sum += sumMax - sumMin;
        }

        return new Pair(count, sum);
    }

    private long totalRangeSum(int[] nums) {
        int n = nums.length;
        long[] leftMax = new long[n], rightMax = new long[n];
        long[] leftMin = new long[n], rightMin = new long[n];

        Deque<Integer> st = new ArrayDeque<>();

        for (int i = 0; i < n; i++) {
            while (!st.isEmpty() && nums[st.peek()] <= nums[i]) st.pop();
            leftMax[i] = st.isEmpty() ? i + 1 : i - st.peek();
            st.push(i);
        }

        st.clear();
        for (int i = n - 1; i >= 0; i--) {
            while (!st.isEmpty() && nums[st.peek()] < nums[i]) st.pop();
            rightMax[i] = st.isEmpty() ? n - i : st.peek() - i;
            st.push(i);
        }

        st.clear();
        for (int i = 0; i < n; i++) {
            while (!st.isEmpty() && nums[st.peek()] >= nums[i]) st.pop();
            leftMin[i] = st.isEmpty() ? i + 1 : i - st.peek();
            st.push(i);
        }

        st.clear();
        for (int i = n - 1; i >= 0; i--) {
            while (!st.isEmpty() && nums[st.peek()] > nums[i]) st.pop();
            rightMin[i] = st.isEmpty() ? n - i : st.peek() - i;
            st.push(i);
        }

        long ans = 0;
        for (int i = 0; i < n; i++) {
            ans += 1L * nums[i] * leftMax[i] * rightMax[i];
            ans -= 1L * nums[i] * leftMin[i] * rightMin[i];
        }

        return ans;
    }
}