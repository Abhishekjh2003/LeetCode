import java.util.*;

class Solution {

    public int minMoves(String[] classroom, int energy) {

        int m = classroom.length;
        int n = classroom[0].length();

        int startR = -1;
        int startC = -1;

        List<int[]> litter = new ArrayList<>();

        // Find S and all L positions
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {

                char ch = classroom[i].charAt(j);

                if (ch == 'S') {
                    startR = i;
                    startC = j;
                }

                if (ch == 'L') {
                    litter.add(new int[]{i, j});
                }
            }
        }

        int k = litter.size();

        if (k == 0) {
            return 0;
        }

        int allMask = (1 << k) - 1;

        boolean[][][][] visited =
            new boolean[m][n][energy + 1][1 << k];

        Queue<State> queue = new LinkedList<>();

        queue.offer(new State(startR, startC, energy, 0));

        visited[startR][startC][energy][0] = true;

        int[][] directions = {
            {1, 0},
            {-1, 0},
            {0, 1},
            {0, -1}
        };

        int moves = 0;

        while (!queue.isEmpty()) {

            int size = queue.size();

            while (size-- > 0) {

                State cur = queue.poll();

                if (cur.mask == allMask) {
                    return moves;
                }

                for (int[] dir : directions) {

                    int nr = cur.r + dir[0];
                    int nc = cur.c + dir[1];

                    // Outside grid
                    if (nr < 0 || nr >= m ||
                        nc < 0 || nc >= n) {
                        continue;
                    }

                    // Wall
                    if (classroom[nr].charAt(nc) == 'X') {
                        continue;
                    }

                    // No energy to move
                    if (cur.energy == 0) {
                        continue;
                    }

                    int newEnergy = cur.energy - 1;
                    int newMask = cur.mask;

                    char cell = classroom[nr].charAt(nc);

                    // Clean litter
                    if (cell == 'L') {

                        for (int i = 0; i < k; i++) {

                            if (litter.get(i)[0] == nr &&
                                litter.get(i)[1] == nc) {

                                newMask |= (1 << i);
                                break;
                            }
                        }
                    }

                    // Recharge
                    if (cell == 'R') {
                        newEnergy = energy;
                    }

                    if (!visited[nr][nc][newEnergy][newMask]) {

                        visited[nr][nc][newEnergy][newMask] = true;

                        queue.offer(
                            new State(
                                nr,
                                nc,
                                newEnergy,
                                newMask
                            )
                        );
                    }
                }
            }

            moves++;
        }

        return -1;
    }

    static class State {

        int r;
        int c;
        int energy;
        int mask;

        State(int r, int c, int energy, int mask) {
            this.r = r;
            this.c = c;
            this.energy = energy;
            this.mask = mask;
        }
    }
}