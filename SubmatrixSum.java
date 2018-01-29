import java.io.*;
import java.util.*;


class SubmatrixSum{
  public static void main(String[] args){
    int[][] matrix = { 
                  {1,2,3,4,5,6,3,4,5,6},
                  {7,8,9,1,2,3,3,4,5,6},
                  {4,2,5,6,3,2,3,4,5,6},
                  {4,3,2,3,4,6,3,4,5,6},
                  {5,5,5,2,3,5,3,4,5,6},
                  {4,3,2,3,4,6,3,4,5,6},
                  {5,5,5,2,3,5,3,4,5,6}};
    int i = 1, j = 1, m = 2, n = 9;

    System.out.println("===: "+submatrixSum(matrix, i, j, m, n) );

  }

  private static int submatrixSum( int[][] matrix, int i, int j, int m, int n ){
    if( matrix == null || matrix.length == 0 )
      return 0;
    int[][] aux = preProcess( matrix );
    int r = aux.length;
    int c = aux[0].length;

    for( int x = 0; x < r; x++ ){
      System.out.println();
      for( int y = 0; y < c; y++ )
        System.out.print(aux[x][y] + " ");
    }

    System.out.println();
    return findRes(aux, i, j, m, n);
  }

  private static int[][] preProcess(int[][] matrix ){
    int r = matrix.length;
    int c = matrix[0].length;
    int[][] aux = new int[r][c];
    for (int i=0; i<r; i++)
      aux[i][0] = matrix[i][0];

    for( int i = 0; i < r; i++ )
      for( int j = 1; j < c; j++ )
        aux[i][j] = matrix[i][j] + aux[i][j-1];

    for( int j = 0; j < c; j++ )
      for( int i = 1; i < r; i++ )
        aux[i][j] = aux[i-1][j] + aux[i][j];

    return aux;
  }

  private static int findRes( int[][] aux, int i, int j, int m, int n ){
    if( i > m || j > n)
      return 0;
    System.out.println(aux.length + ":" + aux[0].length);

    if( i == 0 && j == 0)
        return aux[m][n];
    else if( i == 0 && j > 0)
        return aux[m][n]-aux[m][j-1];
    else if( j == 0 && i > 0)
        return aux[m][n]-aux[i-1][n];
    return aux[m][n]-aux[m][j-1]-aux[i-1][n]+aux[i-1][j-1];
  }
}