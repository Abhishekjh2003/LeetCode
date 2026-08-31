class Solution {
    public int[] nodesBetweenCriticalPoints(ListNode head) {

        int[] ans = {-1, -1};

        if (head == null || head.next == null || head.next.next == null) {
            return ans;
        }

        ListNode prev = head;
        ListNode curr = head.next;

        int index = 1;

        int first = -1;
        int last = -1;
        int minDistance = Integer.MAX_VALUE;

        while (curr.next != null) {

            int value = curr.val;

            boolean isCritical =
                    (value > prev.val && value > curr.next.val) ||
                    (value < prev.val && value < curr.next.val);

            if (isCritical) {

                if (first == -1) {
                    // First critical point
                    first = index;
                } else {
                    // Distance from previous critical point
                    minDistance = Math.min(
                            minDistance,
                            index - last
                    );
                }

                last = index;
            }

            prev = curr;
            curr = curr.next;
            index++;
        }

        // Need at least two critical points
        if (first == last) {
            return ans;
        }

        ans[0] = minDistance;
        ans[1] = last - first;

        return ans;
    }
}