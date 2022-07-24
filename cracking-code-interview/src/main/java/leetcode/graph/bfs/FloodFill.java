package leetcode.graph.bfs;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Objects;

public class FloodFill { // https://leetcode.com/problems/flood-fill/
  // BFS
  public int[][] floodFill(int[][] image, int sr, int sc, int color) {
    var width = image[0].length;
    var height = image.length;
    var visited = new HashSet<Coords>();
    var queue = new LinkedList<Coords>();
    queue.add(new Coords(sr, sc));
    var startColor = image[sr][sc];

    while (!queue.isEmpty()) {
      var coords = queue.poll();
      if (visited.contains(coords)) continue;

      image[coords.row][coords.column] = color;
      visited.add(coords);

      if (coords.row - 1 >= 0 && image[coords.row - 1][coords.column] == startColor) {
        queue.add(new Coords(coords.row - 1, coords.column));
      }
      if (coords.row + 1 < height && image[coords.row + 1][coords.column] == startColor) {
        queue.add(new Coords(coords.row + 1, coords.column));
      }
      if (coords.column - 1 >= 0 && image[coords.row][coords.column - 1] == startColor) {
        queue.add(new Coords(coords.row, coords.column - 1));
      }
      if (coords.column + 1 < width && image[coords.row][coords.column + 1] == startColor) {
        queue.add(new Coords(coords.row, coords.column + 1));
      }
    }

    return image;
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
    new FloodFill()
        .floodFill(
            new int[][] {new int[] {1, 1, 1}, new int[] {1, 1, 0}, new int[] {1, 0, 1}}, 1, 1, 2);
  }
}
