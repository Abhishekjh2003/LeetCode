class Solution {
    public int singleNumber(int[] nums) {

       Map<Integer,Integer> m1 = new LinkedHashMap<>();


        for(int i=0;i<nums.length;i++)
        {
            m1.put(nums[i],m1.getOrDefault(nums[i],0)+1);

        }
        int y=0;
         for(Map.Entry<Integer,Integer> x:m1.entrySet())
         {
            if(x.getValue()==1){
            
               y=x.getKey();
         }
         }
         return y;
         
    }
}