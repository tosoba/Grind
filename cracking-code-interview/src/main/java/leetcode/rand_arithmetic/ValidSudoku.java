package leetcode.rand_arithmetic;

import java.util.HashSet;

public class ValidSudoku {
  public boolean isValidSudoku(char[][] board) {
    var sub = new HashSet<Character>();
    for (var i = 0; i < board.length; ++i) {
      sub.clear();
      for (var j = 0; j < board[0].length; ++j) {
        if (board[i][j] == '.') continue;
        if (sub.contains(board[i][j])) return false;
        sub.add(board[i][j]);
      }
    }

    for (var i = 0; i < board[0].length; ++i) {
      sub.clear();
      for (var j = 0; j < board.length; ++j) {
        if (board[j][i] == '.') continue;
        if (sub.contains(board[j][i])) return false;
        sub.add(board[j][i]);
      }
    }

    int row = 0, column = 0;
    for (var a = 0; a < 9; ++a) {
      sub.clear();
      for (var i = row; i < row + 3; ++i) {
        for (var j = column; j < column + 3; ++j) {
          if (board[i][j] == '.') continue;
          if (sub.contains(board[i][j])) return false;
          sub.add(board[i][j]);
        }
      }
      column += 3;
      if (column >= board.length) column = 0;
      if (column == 0) row += 3;
    }

    return true;
  }

  public static void main(String[] args) {
    //
  }
}
