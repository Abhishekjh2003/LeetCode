import java.util.*;

class Group {
    public int start;
    public int length;

    public Group(int start, int length) {
        this.start = start;
        this.length = length;
    }
}

class SparseTable {
    private int n;
    private int[][] st;

    public SparseTable(int[] nums) {
        n = nums.length;
        int maxLog = 32 - Integer.numberOfLeadingZeros(n);
        st = new int[maxLog + 1][n + 1];
        System.arraycopy(nums, 0, st[0], 0, n);
        for (int i = 1; i <= maxLog; ++i) {
            for (int j = 0; j + (1 << i) <= n; ++j) {
                st[i][j] = Math.max(st[i - 1][j], st[i - 1][j + (1 << (i - 1))]);
            }
        }
    }

    public int query(int l, int r) {
        int i = 31 - Integer.numberOfLeadingZeros(r - l + 1);
        return Math.max(st[i][l], st[i][r - (1 << i) + 1]);
    }
}

class Solution {
    public List<Integer> maxActiveSectionsAfterTrade(String s, int[][] queries) {
        int n = s.length();
        int ones = 0;
        for (char c : s.toCharArray()) {
            if (c == '1') ones++;
        }

        List<Group> zeroGroups = new ArrayList<>();
        int[] zeroGroupIndex = new int[n];
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == '0') {
                if (i > 0 && s.charAt(i - 1) == '0') {
                    zeroGroups.get(zeroGroups.size() - 1).length++;
                } else {
                    zeroGroups.add(new Group(i, 1));
                }
            }
            zeroGroupIndex[i] = zeroGroups.size() - 1;
        }

        List<Integer> ans = new ArrayList<>(queries.length);
        if (zeroGroups.isEmpty()) {
            for (int i = 0; i < queries.length; i++) {
                ans.add(ones);
            }
            return ans;
        }

        int[] mergeLengths = new int[Math.max(0, zeroGroups.size() - 1)];
        for (int i = 0; i < zeroGroups.size() - 1; i++) {
            mergeLengths[i] = zeroGroups.get(i).length + zeroGroups.get(i + 1).length;
        }

        SparseTable st = new SparseTable(mergeLengths);

        for (int[] query : queries) {
            int l = query[0];
            int r = query[1];

            int left = (zeroGroupIndex[l] == -1) ? -1 : 
                (zeroGroups.get(zeroGroupIndex[l]).length - (l - zeroGroups.get(zeroGroupIndex[l]).start));
            int right = (zeroGroupIndex[r] == -1) ? -1 : 
                (r - zeroGroups.get(zeroGroupIndex[r]).start + 1);

            int startAdjIdx = zeroGroupIndex[l] + 1;
            int endAdjIdx = (s.charAt(r) == '1' ? zeroGroupIndex[r] : zeroGroupIndex[r] - 1) - 1;

            int activeSections = ones;
            if (s.charAt(l) == '0' && s.charAt(r) == '0' && zeroGroupIndex[l] + 1 == zeroGroupIndex[r]) {
                activeSections = Math.max(activeSections, ones + left + right);
            } else if (startAdjIdx <= endAdjIdx) {
                activeSections = Math.max(activeSections, ones + st.query(startAdjIdx, endAdjIdx));
            }

            if (s.charAt(l) == '0' && zeroGroupIndex[l] + 1 <= (s.charAt(r) == '1' ? zeroGroupIndex[r] : zeroGroupIndex[r] - 1)) {
                activeSections = Math.max(activeSections, ones + left + zeroGroups.get(zeroGroupIndex[l] + 1).length);
            }

            if (s.charAt(r) == '0' && zeroGroupIndex[l] < zeroGroupIndex[r] - 1) {
                activeSections = Math.max(activeSections, ones + right + zeroGroups.get(zeroGroupIndex[r] - 1).length);
            }

            ans.add(activeSections);
        }

        return ans;
    }
}