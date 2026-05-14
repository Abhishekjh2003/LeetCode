class Solution {
    public int lengthOfLongestSubstring(String s) {
        HashSet<Character> s1 = new HashSet<>();
        int i=0;
        int maxlength=0;
        for(int j=0;j<s.length();j++)
        {
            while(s1.contains(s.charAt(j)))
            {
                s1.remove(s.charAt(i));

                i++;
            }
            s1.add(s.charAt(j));
            maxlength=Math.max(maxlength,j-i+1);
        }
        return maxlength;
    }
}