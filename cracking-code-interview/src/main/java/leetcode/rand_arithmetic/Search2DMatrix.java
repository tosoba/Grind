package leetcode.rand_arithmetic;

public class Search2DMatrix {
  public boolean searchMatrix(int[][] matrix, int target) {
    int start = 0;
    int end = matrix.length * matrix[0].length;
    int index = -1;
    while (end - start > 1) {
      int mid = (start + end) / 2;
      int row = mid / matrix[0].length;
      int col = mid % matrix[0].length;
      if (matrix[row][col] > target) {
        end = mid;
      } else if (matrix[row][col] < target) {
        start = mid;
      } else {
        index = mid;
        break;
      }
    }

    if (index == -1 && matrix[start / matrix[0].length][start % matrix[0].length] == target) {
      index = start;
    }
    return index != -1;
  }
}
