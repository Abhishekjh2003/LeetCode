class Solution {
    public int search(int[] nums, int target) {
            int index=0;
            boolean found =false;
        for(int i=0;i<nums.length;i++)
        {
            if(nums[i]==target)
            {
                index=  i;
                found =true;
                
            }
        }
        if(!found)
        {
            return -1;

        }else
        {
            return index;
        }

        
    }
}