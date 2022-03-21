package com.sci.cs402.utils;

public class Matrix {

  public static int det(int[][] a) {
    int n = a.length;
    if (n == 1) {
      return a[0][0];
    }
    int res = 0;
    int[][] c = new int[n - 1][n - 1];
    for (int col = 0; col < n; col++) {

      for (int i = 1; i < n; i++) {
        int J = 0;
        for (int j = 0; j < n - 1; j++) {
          if (j == col) {
            J++;
          }
          c[i - 1][j] = a[i][J++];
        }
      }

      res += a[0][col] * det(c) * (col % 2 == 0 ? 1 : -1);
    }
    return res;
  }

  private static int[][] trans(int[][] a) {
    int n = a.length;
    int[][] res = new int[n][n];
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < n; j++) {
        res[i][j] = a[j][i];
      }
    }
    return res;
  }

  public static int[][] adj(int[][] a) {
    int n = a.length;
    int[][] res = new int[n][n];

    int[][] c = new int[n - 1][n - 1];
    for (int row = 0; row < n; row++) {
      for (int col = 0; col < n; col++) {

        int II = 0;
        for (int i = 0; i < n; i++) {
          if (i == row) {
            continue;
          }
          int JJ = 0;
          for (int j = 0; j < n; j++) {
            if (j == col) {
              continue;
            }
            c[II][JJ] = a[i][j];
            JJ++;
          }
          II++;
        }

        res[row][col] = det(c) * ((row + col) % 2 == 0 ? 1 : -1);
      }
    }

    return trans(res);
  }

  public static void main(String[] args) {
//    System.out.println(det(new int[][]{{3, 8}, {4, 6}}));
//    System.out.println(det(new int[][]{{1, 2}, {3, 4}}));
//    System.out.println(det(new int[][]{{6, 1, 1}, {4, -2, 5}, {2, 8, 7}}));

    int[][] mat = adj(new int[][]{{3, 1, -1}, {2, -2, 0}, {1, 2, -1}});
    for (int i = 0; i < mat.length; i++) {
      for (int j = 0; j < mat[i].length; j++) {
        System.out.print(mat[i][j] + " ");
      }
      System.out.println();
    }
  }
}
