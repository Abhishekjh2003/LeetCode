class Solution {
 
    public int minElement(int[] nums) {

       int fmin=Integer.MAX_VALUE;

       for(int i=0;i<nums.length;i++)
       {
          int  res=sum(nums[i]);
       
            if(res<fmin)
            {
                fmin=res;
            }
       }
       return fmin; 
    }

    public static int sum (int num)
    {
        int sum =0;
        while(num!=0)
        {
            int rem =num%10;
            sum+=rem;
            num/=10;  
        }
       return sum; 
    }    
}