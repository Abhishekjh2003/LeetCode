class Solution {
    public int[] twoSum(int[] n, int target) {
        
        int x=0,y=n.length-1;
        while(x<y)
        {
            int sum =n[x]+n[y];
            {
                if(sum== target)
                {
                    return new int[]{x+1,y+1};
                }else if(sum<target)
                {
                    x++;
                }else
                {
                    y--;
                }
            }
        }
        return new int[]{};

   
     
        
    }
}