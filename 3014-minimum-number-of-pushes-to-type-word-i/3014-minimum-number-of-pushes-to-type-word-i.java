class Solution {
    public int minimumPushes(String word) {
        int n = word.length();
        int outputs= 0;
        for (int i = 0; i < n; i++) {
            outputs += (i / 8) + 1;
        }
        return outputs;
    }
}