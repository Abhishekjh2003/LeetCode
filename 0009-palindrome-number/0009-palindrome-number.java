class Solution {
    public boolean isPalindrome(int x) {
        int res=0;
        int xx=x;
        boolean z;
        if(x<0)
        {
            return false;
        }
        while(x!=0)
        {
            int rem=x%10;
            res=res*10+rem;
            x/=10;
        }
      

        return xx==res;
    }
}