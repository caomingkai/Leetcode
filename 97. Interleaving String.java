class Solution {
    public boolean isInterleave(String s1, String s2, String s3) {
        if( s1 == null || s2 == null || s3 == null )
            return false;
        
        int len1 = s1.length();
        int len2 = s2.length();
        int len3 = s3.length();
        if( len1 + len2 != len3 )
            return false;
        
        boolean[][] dp = new boolean[len1+1][len2+1]; // dp[i][j]: s1前i字串与s2前j字串，是否可以构成s3前(i+j)字串
        dp[0][0] = true;
        for(int i = 1; i <= len1; i++){
            dp[i][0] = dp[i-1][0]&&s1.charAt(i-1)==s3.charAt(i-1) ;
        }
        
        for(int j = 1; j <= len2; j++){
            dp[0][j] = dp[0][j-1]&&s2.charAt(j-1)==s3.charAt(j-1) ;
        }
        
        for(int i = 1; i <= len1; i++){
            for(int j = 1; j <= len2; j++){
                dp[i][j] = dp[i-1][j]&&s1.charAt(i-1)==s3.charAt(i+j-1) || 
                           dp[i][j-1]&&s2.charAt(j-1)==s3.charAt(i+j-1);
            }
        }
        return dp[len1][len2];
    }
}