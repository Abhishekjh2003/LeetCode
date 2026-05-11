class Solution {
    public int maxProduct(int[] nums) {
        int maxprod=nums[0];
        int minprod =nums[0];
        int result = nums[0];
        for(int i=1;i<nums.length;i++)
        {
            int tempmax=Math.max(nums[i],Math.max(nums[i]*maxprod,nums[i]*minprod));
            int tempmin=Math.min(nums[i],Math.min(nums[i]*maxprod,nums[i]*minprod));

            maxprod=tempmax;
            minprod=tempmin;

            result =Math.max(result,maxprod);
        }

        return result ;
        
    }
}