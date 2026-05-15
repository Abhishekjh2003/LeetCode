class Solution {
    public String frequencySort(String s) {
        HashMap<Character,Integer> m = new HashMap<>();
        for(char c:s.toCharArray())
        {
            m.put(c,m.getOrDefault(c,0)+1);

        }
        PriorityQueue<Character> pq =new PriorityQueue<>((a,b)->m.get(b)-m.get(a));
        pq.addAll(m.keySet());

        StringBuilder ans = new StringBuilder();
        while(!pq.isEmpty())
        {
            char ch = pq.poll();
            int freq= m.get(ch);

            while(freq-->0)
            {
                ans.append(ch);
            }
        }
        return ans.toString();
    }
}