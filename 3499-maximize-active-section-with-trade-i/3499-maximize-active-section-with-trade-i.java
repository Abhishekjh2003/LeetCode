class Solution {
    public int maxActiveSectionsAfterTrade(String s) {
        int ones = 0;

        for (char c : s.toCharArray()) {
            if (c == '1') ones++;
        }

        List<Integer> zeroGroups = new ArrayList<>();

        int i = 0;
        while (i < s.length()) {
            if (s.charAt(i) == '0') {
                int j = i;
                while (j < s.length() && s.charAt(j) == '0') {
                    j++;
                }
                zeroGroups.add(j - i);
                i = j;
            } else {
                i++;
            }
        }

        int maxGain = 0;

        for (int k = 0; k + 1 < zeroGroups.size(); k++) {
            maxGain = Math.max(maxGain,
                    zeroGroups.get(k) + zeroGroups.get(k + 1));
        }

        return ones + maxGain;
    }
}
