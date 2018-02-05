/*
    1 - 对于finish point来说，可行路径“只能”经由两各方向到达：上边，左边；
        那么numPath(finish) = numPath(top) + numPath(left);
    2 - 图中的每个方块，都是这种情况: numPath(node) = numPath(top) + numPath(left)
    3 - 除了特殊情况：
        + 上边缘方块，只有一种走法 numPath(node) = numPath(left)
        + 左边缘方块，只有一种走法 numPath(node) = numPath(top)

    DP
*/
/*
class Solution {
    public int uniquePaths(int m, int n) {
        if( m <= 0 || n <= 0 )
            return 0;
        
        int[][] dp = new int[m][n];
        
        for( int i = 0; i < m; i++ ){
            for( int j = 0; j < n; j++ ){
                if( i == 0 && j == 0 ){
                    dp[i][j] = 1;
                }else if( i == 0 )
                    dp[i][j] = dp[i][j-1];
                else if( j == 0 )
                    dp[i][j] = dp[i-1][j];
                else
                    dp[i][j] = dp[i-1][j] + dp[i][j-1];
            }
        }
        return dp[m-1][n-1];
    }
}
*/


/*
Space Complexity: O(m*n)  --->  O(n)

对于从(0,0)到(i,j)的路径总数：
ways[j] =  起点到点(i-1, j) 的路径总数：ways[j] ( i 上一次计算的值)
         + 起点到点(i, j-1)的路径总数 ways[j-1]，
于是我们就得到递推式：ways[j] = ways[j] + ways[j-1]
*/
public class Solution {  
    public int uniquePaths(int m, int n) {  
        int[] ways = new int[n];  
        ways[0] = 1;  
        for(int i = 0; i < m; i++)  
            for (int j = 1; j < n; j++)  
                ways[j] += ways[j-1];  
        return ways[n-1];  
    }  
}