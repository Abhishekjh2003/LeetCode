class Solution {
    public int maxArea(int[] height) {
        int n= height.length;
        int i=0;int j = n-1;
        int maxarea=0;

        while(i<j)
        {
            int h= Math.min(height[i],height[j]);
            int w= j-i;
            int area =h*w;
            maxarea =Math.max(maxarea,area);


        if(height[i]<height[j])
        {
            i++;
        }
        else
        {
            j--;
        }

        }

        return  maxarea;
        
    }
}