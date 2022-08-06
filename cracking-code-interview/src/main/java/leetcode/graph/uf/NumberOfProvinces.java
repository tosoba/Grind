package leetcode.graph.uf;

import java.util.ArrayDeque;
import java.util.HashSet;

public class NumberOfProvinces {
  // optimal solution using Union find
  public int findCircleNumUF(int[][] isConnected) {
    var uf = new UnionFind(isConnected);
    return uf.getCount();
  }

  private static final class UnionFind {
    private int count;
    private final int[] roots;

    public UnionFind(int[][] isConnected) {
      count = isConnected.length;
      roots = new int[isConnected.length];
      for (var i = 0; i < isConnected.length; ++i) {
        roots[i] = i;
      }

      for (var i = 0; i < isConnected.length; ++i) {
        for (var j = 0; j < i; ++j) {
          if (isConnected[i][j] == 1) union(i, j);
        }
      }
    }

    private void union(int node1, int node2) {
      int root1 = findRoot(node1);
      int root2 = findRoot(node2);
      if (root1 == root2) return;

      roots[root1] = root2;
      count--;
    }

    private int findRoot(int node) {
      if (roots[node] == node) return node;
      return findRoot(roots[node]);
    }

    public int getCount() {
      return count;
    }
  }

  // BFS - faster solution possible using union find
  public int findCircleNum(int[][] isConnected) {
    var inProvince = new HashSet<Integer>();
    int provincesCount = 0;

    for (var start = 0; start < isConnected.length; ++start) {
      if (inProvince.contains(start)) continue;

      var queue = new ArrayDeque<Integer>();
      var visited = new HashSet<Integer>();
      queue.add(start);
      while (!queue.isEmpty()) {
        var city = queue.poll();
        if (visited.contains(city)) continue;
        visited.add(city);

        for (var neigbour = 0; neigbour < isConnected[city].length; ++neigbour) {
          if (isConnected[city][neigbour] == 0) continue;
          queue.add(neigbour);
        }
      }

      inProvince.addAll(visited);
      ++provincesCount;
    }

    return provincesCount;
  }

  public static void main(String[] args) {
    System.out.println(
        new NumberOfProvinces()
            .findCircleNumUF(
                new int[][] {
                  new int[] {1, 0, 0, 1},
                  new int[] {0, 1, 1, 0},
                  new int[] {0, 1, 1, 1},
                  new int[] {1, 0, 1, 1}
                }));
  }
}
