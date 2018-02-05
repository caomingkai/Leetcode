/*  
    http://www.cnblogs.com/higerzhang/p/4109278.html
    https://www.jianshu.com/p/cffdc94c9c19
    http://www.cnblogs.com/higerzhang/p/4109278.html
    
    思路是对每一行，把它上面的所有行往下做投影，比如上面的矩形会变成：
    10100
    20211
    31322
    40030
    然后对每一行用84题Largest Rectangle in Histogram的方法求解最大矩阵。
    
    
    
    1. DP 求 height
    2. 对每一行求 Largest Rectangle in Histogram 
*/

class Solution {
    public int maximalRectangle(boolean[][] matrix) {

        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }

        //  obtain the height for each row
        int n = matrix.length;
        int m = matrix[0].length;
        int[][] height = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (i == 0) {
                    height[i][j] = matrix[i][j] ? 1 : 0;
                } else {
                    height[i][j] = matrix[i][j] ? 1 + height[i-1][j] : 0;
                }
            }
        }

        
        // find "Largest Rectangle in Histogram" for each row
        int res = 0;
        for (int i = 0; i < n; i++) {
            Stack<Integer> stack = new Stack<Integer>();
            for (int j = 0; j <= m; j++) {
                int nowHeight = j == m ? -1 : height[i][j];
                while (!stack.isEmpty() && height[i][stack.peek()] >= nowHeight) {
                    int h = height[i][stack.pop()];
                    int w = stack.isEmpty() ? j : j - stack.peek() - 1;
                    res = Math.max(res, h*w);
                }
                stack.push(j);
            }
        }

        return res;
    }

}