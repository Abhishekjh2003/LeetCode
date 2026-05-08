class Solution {
    public int addDigits(int num) {
        

        if(num <10)
        {
            return num;
        }

        while(num >9)
        {

                num=sum(num);
        }

        return num;
        

        
    }

    public static int sum(int num)
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