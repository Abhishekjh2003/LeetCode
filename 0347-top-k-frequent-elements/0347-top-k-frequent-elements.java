class Solution {
    public int[] topKFrequent(int[] nums, int k) {
       
        Map <Integer,Integer> m1 = new LinkedHashMap<>();
        for(int i=0;i<nums.length;i++)

        {
            m1.put(nums[i],m1.getOrDefault(nums[i],0)+1);

        }
         List<Integer> l1 = new LinkedList<>(m1.keySet());


         Collections.sort(l1,(a,b) ->m1.get(b)-m1.get(a));


         int a[]= new int[k];
         for(int i=0;i<k;i++)
         {
            a[i]=l1.get(i);
        }


        return a;     
    }
}