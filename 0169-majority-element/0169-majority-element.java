class Solution {
    public int majorityElement(int[] nums) {

            int y=0;

        Map<Integer,Integer> m1 = new LinkedHashMap<>();

        for(int i=0;i<nums.length;i++)
        {
            m1.put(nums[i],m1.getOrDefault(nums[i],0)+1);
        }
        for(Map.Entry<Integer,Integer> x:m1.entrySet())
        
        {
            if(x.getValue()>nums.length/2)
            {
                y= x.getKey();
            }
        }
        return y;
        
    }
}