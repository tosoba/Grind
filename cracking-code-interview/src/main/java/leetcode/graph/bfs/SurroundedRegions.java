package leetcode.graph.bfs;

import java.util.*;

public class SurroundedRegions { // https://leetcode.com/problems/surrounded-regions/
  // BFS
  public void solve(char[][] board) {
    var width = board[0].length;
    var height = board.length;
    var visited = new HashSet<Coords>();
    var queue = new LinkedList<Coords>();
    for (var row = 0; row < height; ++row) {
      for (var col = 0; col < width; ++col) {
        if (board[row][col] == 'X' || visited.contains(new Coords(row, col))) continue;

        var island = new HashSet<Coords>();
        var surrounded = true;
        queue.add(new Coords(row, col));
        while (!queue.isEmpty()) {
          var coords = queue.poll();
          if (visited.contains(coords)) continue;

          visited.add(coords);
          if (surrounded) island.add(coords);
          else if (!island.isEmpty()) island.clear();

          if (coords.row - 1 >= 0 && board[coords.row - 1][coords.column] == 'O') {
            queue.add(new Coords(coords.row - 1, coords.column));
          } else if (coords.row - 1 < 0) {
            surrounded = false;
          }

          if (coords.row + 1 < height && board[coords.row + 1][coords.column] == 'O') {
            queue.add(new Coords(coords.row + 1, coords.column));
          } else if (coords.row + 1 >= height) {
            surrounded = false;
          }

          if (coords.column - 1 >= 0 && board[coords.row][coords.column - 1] == 'O') {
            queue.add(new Coords(coords.row, coords.column - 1));
          } else if (coords.column - 1 < 0) {
            surrounded = false;
          }

          if (coords.column + 1 < width && board[coords.row][coords.column + 1] == 'O') {
            queue.add(new Coords(coords.row, coords.column + 1));
          } else if (coords.column + 1 >= width) {
            surrounded = false;
          }
        }
        if (surrounded) {
          island.forEach(coords -> board[coords.row][coords.column] = 'X');
        }
      }
    }
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
