/*

// version 1: dp 
public class Solution {
    public int numDecodings(String s) {
        if(s == null || s.length() == 0) {
            return 0;
        }
        int n = s.length();
        int[] dp = new int[n+1];
        dp[0] = 1;
        dp[1] = s.charAt(0) != '0' ? 1 : 0;
        for(int i = 2; i <= n; i++) {
            int first = Integer.valueOf(s.substring(i-1, i));
            int second = Integer.valueOf(s.substring(i-2, i));
            if(first >= 1 && first <= 9) {
               dp[i] += dp[i-1];  
            }
            if(second >= 10 && second <= 26) {
                dp[i] += dp[i-2];
            }
        }
        return dp[n];
    }
}

*/
    
   
// version 2:  recursioin

class Solution {
    HashMap<String, Integer> map = new HashMap<>();
    
    public int numDecodings(String s) {
        if( s == null || s.length() == 0 || s.charAt(0)=='0')
            return 0;
        return h( s );
    }
    
    private int h( String s ){
        
        // check cache
        if( map.containsKey(s) )
            return map.get(s);
        
        // base case:
        if( s.length() == 0 ) return 1;
        if( s.length() == 1){
            if( s.charAt(0) > '0' )
                return 1;
            else
                return 0;
        } 
        
        // general case:
        int ret = 0;
        int first = s.charAt(0)-'0';
        int second = s.charAt(1)-'0';
        
        if( first != 0 ){
            ret += h(s.substring(1) );
            if( first*10 + second <=26 )
                ret += h(s.substring(2) );
        }
        map.put( s, ret );
        return ret;
    }
}

