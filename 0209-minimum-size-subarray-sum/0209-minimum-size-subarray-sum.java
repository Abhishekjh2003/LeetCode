class Solution {
    public int minSubArrayLen(int target, int[] nums) {
        int i=0;
        int sum =0;
        int minlen =Integer.MAX_VALUE;

        for(int k =0;k<nums.length;k++)
        {
            sum +=nums[k];

            while(sum>=target )
            {
                minlen = Math.min(minlen,k-i+1);
                sum -=nums[i];
                i++;
            }
        }
        return minlen== Integer.MAX_VALUE? 0:minlen;

    }
}