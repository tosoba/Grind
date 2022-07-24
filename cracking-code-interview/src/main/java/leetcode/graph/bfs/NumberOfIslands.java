package leetcode.graph.bfs;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Objects;

public class NumberOfIslands { // https://leetcode.com/problems/number-of-islands/
  // BFS
  public int numIslands(char[][] grid) {
    var width = grid[0].length;
    var height = grid.length;
    var visited = new HashSet<Coords>();
    var queue = new LinkedList<Coords>();
    int ans = 0;
    for (var row = 0; row < height; ++row) {
      for (var col = 0; col < width; ++col) {
        if (grid[row][col] == '0' || visited.contains(new Coords(row, col))) continue;

        queue.add(new Coords(row, col));
        while (!queue.isEmpty()) {
          var coords = queue.poll();
          if (visited.contains(coords)) continue;

          visited.add(coords);
          if (coords.row - 1 >= 0 && grid[coords.row - 1][coords.column] == '1') {
            queue.add(new Coords(coords.row - 1, coords.column));
          }
          if (coords.row + 1 < height && grid[coords.row + 1][coords.column] == '1') {
            queue.add(new Coords(coords.row + 1, coords.column));
          }
          if (coords.column - 1 >= 0 && grid[coords.row][coords.column - 1] == '1') {
            queue.add(new Coords(coords.row, coords.column - 1));
          }
          if (coords.column + 1 < width && grid[coords.row][coords.column + 1] == '1') {
            queue.add(new Coords(coords.row, coords.column + 1));
          }
        }
        ++ans;
      }
    }
    return ans;
  }

  private static final class Coords {
    int row;
    int column;

    public Coords(int row, int column) {
      this.row = row;
      this.column = column;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;
      Coords coords = (Coords) o;
      return row == coords.row && column == coords.column;
    }

    @Override
    public int hashCode() {
      return Objects.hash(row, column);
    }
  }

  public static void main(String[] args) {
    //
  }
}
