class Solution {
    public int earliestFinishTime(int[] landStartTime, int[] landDuration, int[] waterStartTime, int[] waterDuration) {
        
        // int res= Integer.MAX_VALUE;

        // for(int  i=0;i<landStartTime.length;i++)
        // {
        //     int landend=landStartTime[i] + landDuration[i];

        //     for(int j=0; j<waterStartTime.length;j++)
        //     {
        //         int finish = Math.max(landend, waterStartTime[j]) + waterDuration[j];

        //         res= Math.min(res,finish);

        //     }
        // }
        // for(int  i=0; i<waterStartTime.length;i++)
        // {
        //     int waterEnd = waterStartTime[i] + waterDuration[i];

        //     for(int j=0; j<landStartTime.length;j++)
        //     {
        //         int finish =Math.max(waterEnd,landStartTime[j]+landDuration[j]);
        //         res=Math.min(res,finish);
        //     }
        // }
        // return res;



        int ans = Integer.MAX_VALUE;

        // Try: land first, then best water
        for (int i = 0; i < landStartTime.length; i++) {
            int landEnd = landStartTime[i] + landDuration[i];

            for (int j = 0; j < waterStartTime.length; j++) {
                int finish = Math.max(landEnd, waterStartTime[j]) + waterDuration[j];
                ans = Math.min(ans, finish);
            }
        }

        // Try: water first, then best land
        for (int i = 0; i < waterStartTime.length; i++) {
            int waterEnd = waterStartTime[i] + waterDuration[i];

            for (int j = 0; j < landStartTime.length; j++) {
                int finish = Math.max(waterEnd, landStartTime[j]) + landDuration[j];
                ans = Math.min(ans, finish);
            }
        }

        return ans;
    }
}