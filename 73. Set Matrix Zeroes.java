/* 
      0   1   2   3   4
     ___________________
 0  |   |   | 0 |   |   | 
    |___|___|___|___|___|
 1  |   |   |   |   |   | 
    |___|___|___|___|___|
 2  | 0 |   | 0 |   |   |  
    |___|___|___|___|___|
 3  |   |   |   |   |   |  
    |___|___|___|___|___|

    
    1. 关键点：[2,2]：0的效果，相当于[0,2]：0 与[2,0]：0的综合效果
    2. 这样将matrix中所有的0，转移到第0行、第0列
    3. 第0行、第0列是否需要被置零，则只需要两个boolean变量记录既可以
    
    注意点：
    - 用matrix[0][j]、matrix[i][0]来记录 matrix中哪些行、列需要置零时，只需要在对应位置记录“0”即可
      不需要将matrix[0][j]提前置零，不需要用“1”来记录（浪费时间）
    - 检查第0行、第0列是否需要置零，可以于上述过程一同进行
    - 置零时，只需要对表格扫一遍就行，（只要对应的matrix[0][j]、matrix[i][0] 有一个为“0”，该位置就需要置零）

*/
public class Solution {
    public void setZeroes(int[][] matrix) {
        boolean fr = false,fc = false;
        for(int i = 0; i < matrix.length; i++) {
            for(int j = 0; j < matrix[0].length; j++) {
                if(matrix[i][j] == 0) {
                    if(i == 0) fr = true;
                    if(j == 0) fc = true;
                    matrix[0][j] = 0;
                    matrix[i][0] = 0;
                }
            }
        }
        for(int i = 1; i < matrix.length; i++) {
            for(int j = 1; j < matrix[0].length; j++) {
                if(matrix[i][0] == 0 || matrix[0][j] == 0) {
                    matrix[i][j] = 0;
                }
            }
        }
        if(fr) {
            for(int j = 0; j < matrix[0].length; j++) {
                matrix[0][j] = 0;
            }
        }
        if(fc) {
            for(int i = 0; i < matrix.length; i++) {
                matrix[i][0] = 0;
            }
        }

    }
}