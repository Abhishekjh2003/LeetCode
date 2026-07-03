class Solution {
    public int findMaxPathScore(int[][] edges, boolean[] online, long k) {

        int n = online.length;

        List<int[]>[] graph = new ArrayList[n];
        int[] indegree = new int[n];

        int low = Integer.MAX_VALUE;
        int high = Integer.MIN_VALUE;

        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            int w = edge[2];

            graph[u].add(new int[]{v, w});
            indegree[v]++;

            low = Math.min(low, w);
            high = Math.max(high, w);
        }

        int ans = -1;

        while (low <= high) {

            int mid = low + (high - low) / 2;

            if (canReach(mid, graph, indegree, online, k)) {
                ans = mid;
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        return ans;
    }

    private boolean canReach(int score,
                             List<int[]>[] graph,
                             int[] indegree,
                             boolean[] online,
                             long k) {

        int n = graph.length;

        int[] deg = indegree.clone();

        Queue<Integer> queue = new LinkedList<>();

        for (int i = 0; i < n; i++) {
            if (deg[i] == 0) {
                queue.offer(i);
            }
        }

        long INF = Long.MAX_VALUE / 4;

        long[] dist = new long[n];
        Arrays.fill(dist, INF);
        dist[0] = 0;

        while (!queue.isEmpty()) {

            int u = queue.poll();

            for (int[] edge : graph[u]) {

                int v = edge[0];
                int w = edge[1];

                deg[v]--;
                if (deg[v] == 0) {
                    queue.offer(v);
                }

                if (w < score) {
                    continue;
                }

                if (v != n - 1 && !online[v]) {
                    continue;
                }

                if (dist[u] == INF) {
                    continue;
                }

                dist[v] = Math.min(dist[v], dist[u] + w);
            }
        }

        return dist[n - 1] <= k;
    }
}