class Solution {
    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> result = new ArrayList<>();

        if (s.length() < p.length()) {
            return result;
        }

        int[] pFreq = new int[26];
        int[] winFreq = new int[26];

        for (char ch : p.toCharArray()) {
            pFreq[ch - 'a']++;
        }

        int k = p.length();

        for (int i = 0; i < s.length(); i++) {
            winFreq[s.charAt(i) - 'a']++;

            if (i >= k) {
                winFreq[s.charAt(i - k) - 'a']--;
            }

            if (i >= k - 1) {
                if (Arrays.equals(pFreq, winFreq)) {
                    result.add(i - k + 1);
                }
            }
        }

        return result;
    }
}