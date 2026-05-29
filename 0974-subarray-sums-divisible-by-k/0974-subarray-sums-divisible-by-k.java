class Solution {
    public int subarraysDivByK(int[] nums, int k) {
        int sum =0;
        int count =0;
        int freq[]= new int [k];
        freq[0]=1;

        for(int num:nums)
        {
            sum =sum+num;

            int rem = sum%k;
            
            if(rem<0)
            {
                rem = rem +k;

            }

            count = count+freq[rem];
            freq[rem]++;
        }
        return count;
    }
}