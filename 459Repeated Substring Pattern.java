
// either of two categoriey for this pattern should be match: 
// 1. abab     0~n/2 == n/2~n
// 2. ababab   0~n/3 == n/3~2n/3 == 2n/3~n
public class Solution {
    public boolean repeatedSubstringPattern(String s) {
        if( s == null || s.length() < 2) return false;
        if( s.length() == 2 ) return s.charAt(0) == s.charAt(1);
        
        // handle odd length exclusively
        if( s.length() % 2 == 1 ) return secondCase(s);
        
        // handle even length
        return firstCase(s) || secondCase(s);
    }
    
    // 1. abab     0~n/2 == n/2~n
    private boolean firstCase( String s ){
        int l = s.length();
        int mid = l/2;
        for( int i = 0; i < l/2; i++){
            if( s.charAt(i) != s.charAt( i+mid ) ){
                return false;
            }
        }
        return true;
    }
    
    // 2. ababab   0~n/3 == n/3~2n/3 == 2n/3~n
    private boolean secondCase( String s ){
        int l = s.length();
        int triMid = l/3;
        for( int i = 0; i < l/3; i++){
            if( s.charAt(i) != s.charAt( i+triMid )  || s.charAt(i) != s.charAt( i+2*triMid ) ){
                return false;
            }
        }
        return true;
    }
}