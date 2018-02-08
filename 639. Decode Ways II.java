

public class Solution {
    int M = 1000000007;
    public int numDecodings(String s) {
        long[] dp = new long[s.length() + 1];
        dp[0] = 1;
        dp[1] = s.charAt(0) == '*' ? 9 : s.charAt(0) == '0' ? 0 : 1;
        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i) == '*') {
                dp[i + 1] = 9 * dp[i];
                if (s.charAt(i - 1) == '1')
                    dp[i + 1] = (dp[i + 1] + 9 * dp[i - 1]) % M;
                else if (s.charAt(i - 1) == '2')
                    dp[i + 1] = (dp[i + 1] + 6 * dp[i - 1]) % M;
                else if (s.charAt(i - 1) == '*')
                    dp[i + 1] = (dp[i + 1] + 15 * dp[i - 1]) % M;
            } else {
                dp[i + 1] = s.charAt(i) != '0' ? dp[i] : 0;
                if (s.charAt(i - 1) == '1')
                    dp[i + 1] = (dp[i + 1] + dp[i - 1]) % M;
                else if (s.charAt(i - 1) == '2' && s.charAt(i) <= '6')
                    dp[i + 1] = (dp[i + 1] + dp[i - 1]) % M;
                else if (s.charAt(i - 1) == '*')
                    dp[i + 1] = (dp[i + 1] + (s.charAt(i) <= '6' ? 2 : 1) * dp[i - 1]) % M;
            }
        }
        return (int) dp[s.length()];
    }
}



/*   
// BUG!!!
class Solution {
    
    // DP : dp[i] 表示从 0～i 的解码方式
    public int numDecodings(String s) {
        if( s == null || s.length() == 0 )
            return 0;
        
        int len = s.length();
        int[] dp = new int[len+1];
        dp[0] = 1;
        char firstC = s.charAt(0);
        if( firstC != '0' )
            dp[1] = 1;
        else if( firstC == '*' )
            dp[1] = 9;
        else
            dp[1] = 0;
        
        for( int i = 2; i < len+1; i++ ){
            int cnt = 0;
            char first = s.charAt(i-1);
            char second = s.charAt(i-2);

            int fInt = 0;
            int sInt = 0;
            if( second != '*'){
                if( first != '*' ){
                    fInt = first - '0';
                    sInt = second -'0';
                    if( 1<=fInt && fInt <=9 )
                        cnt += dp[i-1];
                    if( 10<=(sInt*10+fInt) && (sInt*10+fInt) <= 26 )
                        cnt += dp[i-2];
                }else{
                    sInt = second -'0';
                    cnt += 9*dp[i-1];
                    if( sInt == 1 )
                        cnt += 10*dp[i-2];
                    if( sInt == 2 )
                        cnt += 7*dp[i-2];
                }
            }else{
                if( first != '*' ){
                    fInt = first - '0';
                    if( 1<=fInt && fInt <=9 )
                        cnt += dp[i-1];
                    if( 0<=fInt && fInt <=6 )
                        cnt += 2*dp[i-2];
                    if( 7<=fInt && fInt <=9 )
                        cnt += dp[i-2];
                }else{
                    cnt += 9*dp[i-1] + 17*dp[i-2];
                }
            }
            dp[i] = cnt;
            System.out.println(i+" : " +dp[i]);
        }
        return dp[len];
    }
}
*/