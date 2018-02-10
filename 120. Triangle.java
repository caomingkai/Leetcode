/*
    含义：   dp[i][j] 从[0,0]到[i,j]所构成路径的 minSum
    递推公式：dp[i][j] = Max.min( dp[i-1][j-1], dp[i-1][j] ) + triangle.get(i).get(j);
    边界条件：  - if(i=0) dp[i][j] = dp[i-1][j] + triangle.get(i).get(j);
              - if(i=length-1) dp[i][j] = dp[i-1][j-1] + triangle.get(i).get(j);
    

*/
class Solution {
    /*
    // version 0:   dp  2-dimension   List<List<Integer>>
    public int minimumTotal(List<List<Integer>> triangle) {
        if( triangle == null || triangle.size() == 0 || triangle.get(0).size() == 0 )
            return 0;
        
        List<List<Integer>> dp = new ArrayList<>();
        List<Integer> row1 = new ArrayList<>();
        row1.add(triangle.get(0).get(0));
        dp.add( row1 );
        int min = Integer.MAX_VALUE;
        
        for( int i = 1 ; i < triangle.size(); i++ ){
            List<Integer> rowList = triangle.get(i);
            List<Integer> rowDp = new ArrayList<>();
            for( int j = 0; j < rowList.size(); j++ ){
                int curSum = 0;
                if( j == 0 ){
                    curSum = dp.get(i-1).get(j);
                }else if( j == rowList.size()-1 ){
                    curSum = dp.get(i-1).get(j-1);
                }else{
                    curSum = Math.min(dp.get(i-1).get(j-1), dp.get(i-1).get(j));
                }
                rowDp.add( curSum + rowList.get(j) );
                if( i == triangle.size() - 1)
                    min = Math.min( min, curSum + rowList.get(j) );
            }
            dp.add(rowDp);
        }
        return min == Integer.MAX_VALUE ? triangle.get(0).get(0) : min ;
    }
    
    // version 2: dp   1-dimension   ArrayList
    public int minimumTotal(List<List<Integer>> triangle) {
        if( triangle == null || triangle.size() == 0 || triangle.get(0).size() == 0 )
            return 0;
                
        List<Integer> dp = new ArrayList<>();
        dp.add(triangle.get(0).get(0));
        int min = Integer.MAX_VALUE;
        
        for( int i = 1 ; i < triangle.size(); i++ ){
            List<Integer> rowList = triangle.get(i);
            for( int j = rowList.size()-1; j >= 0; j-- ){  //【注意】：从后往前 更新
                dp.add(0); // 增加一个元素(dummy val)        // 因为dp[j] = dp[j-1] + dp[j]会用到之前的值
                int curSum = 0;                            // 如果从前往后更新的话，后续值会覆盖前边值
                if( j == 0 ){                              // 所以从后往前更新，先使用旧值，然后更新该值
                    curSum = dp.get(j);
                }else if( j == rowList.size()-1 ){
                    curSum = dp.get(j-1);
                }else{
                    curSum = Math.min(dp.get(j-1), dp.get(j));
                }
                dp.set( j, curSum + rowList.get(j) );
                if( i == triangle.size() - 1)
                    min = Math.min( min, curSum + rowList.get(j) );
            }
        }
        return min == Integer.MAX_VALUE ? triangle.get(0).get(0) : min ;
    }
    */
        
    // version 3: dp 1-dimension  int[ ]
    public int minimumTotal(List<List<Integer>> triangle) {
        if( triangle == null || triangle.size() == 0 || triangle.get(0).size() == 0 )
            return 0;
        
        int len = triangle.size();
        int[] dp = new int[len];
        
        dp[0] = triangle.get(0).get(0);
        int min = Integer.MAX_VALUE;
        
        for( int i = 1 ; i < triangle.size(); i++ ){
            List<Integer> rowList = triangle.get(i);
            for( int j = rowList.size()-1; j >= 0; j-- ){  //【注意】：从后往前 更新
                                                           // 因为dp[j] = dp[j-1] + dp[j]会用到之前的值
                int curSum = 0;                            // 如果从前往后更新的话，后续值会覆盖前边值
                if( j == 0 ){                              // 所以从后往前更新，先使用旧值，然后更新该值
                    curSum = dp[j];
                }else if( j == rowList.size()-1 ){
                    curSum = dp[j-1];
                }else{
                    curSum = Math.min(dp[j], dp[j-1]);
                }
                dp[j] = curSum + rowList.get(j);
                if( i == triangle.size() - 1)
                    min = Math.min( min, curSum + rowList.get(j) );
            }
        }
        return min == Integer.MAX_VALUE ? triangle.get(0).get(0) : min ;
    }
}