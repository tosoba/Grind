package leetcode.dp;

import java.util.Arrays;

public class MinimumPathSum {
    public int minPathSum(int[][] grid) {
        if (grid.length == 0) return 0;
        if (grid.length == 1) return Arrays.stream(grid[0]).sum();

        int[][] dist = new int[grid.length][grid[0].length];
        dist[0][0] = grid[0][0];
        for (int i = 1; i < grid[0].length; ++i) {
            dist[0][i] = dist[0][i - 1] + grid[0][i];
        }

        for (int i = 1; i < grid.length; ++i) {
            dist[i][0] = dist[i - 1][0] + grid[i][0];
        }

        for (int i = 1; i < grid.length; ++i) {
            for (int j = 1; j < grid[0].length; ++j) {
                dist[i][j] = Math.min(dist[i - 1][j], dist[i][j - 1]) + grid[i][j];
            }
        }

        return dist[grid.length - 1][grid[0].length - 1];
    }

    public static void main(String[] args) {

    }
}
