package leetcode.dp;

public class UniquePaths {
    public int uniquePaths(int m, int n) {
        if (n == 1 || m == 1) return 1;

        int[][] ways = new int[m][n];
        ways[0][0] = 1;
        for (var i = 1; i < m; ++i) {
            ways[i][0] = 1;
        }
        for (var i = 1; i < n; ++i) {
            ways[0][i] = 1;
        }
        for (var i = 1; i < m; ++i) {
            for (var j = 1; j < n; ++j) {
                ways[i][j] = ways[i - 1][j] + ways[i][j - 1];
            }
        }

        return ways[m - 1][n - 1];
    }

    public int uniquePathsOptimized(int m, int n) {
        if (n == 1 || m == 1) return 1;

        int[] ways = new int[n - 1];
        for (var i = 0; i < n - 1; ++i) {
            ways[i] = i + 2;
        }

        for (var j = 0; j < m - 2; ++j) {
            ways[0] = ways[0] + 1;
            for (var i = 1; i < n - 1; ++i) {
                ways[i] = ways[i - 1] + ways[i];
            }
        }

        return ways[n - 2];
    }

    public static void main(String[] args) {
        System.out.println(new UniquePaths().uniquePathsOptimized(3, 7));
    }
}
