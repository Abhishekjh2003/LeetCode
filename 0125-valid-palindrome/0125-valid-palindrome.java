class Solution {
    public boolean isPalindrome(String s) {

        String s1=s.replaceAll("[^a-zA-Z0-9]","").toLowerCase();
        char []a =s1.toCharArray();
        int i=0,j=a.length-1;
        while(i<j)
        {
          if(a[i]!=a[j])
          {
            return false;
          }
          i++;
          j--;
        }
        return true;
        
    }
}