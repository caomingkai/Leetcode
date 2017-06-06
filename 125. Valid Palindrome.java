
// version 1: regular express.  
// 1. String .replaceAll("[^A-Za-z0-9]","")
// 2. StringBuilder.reverse()
public class Solution {
    public boolean isPalindrome(String s) {
        String actual = s.replaceAll("[^A-Za-z0-9]", "").toLowerCase();
        String rev = new StringBuffer(actual).reverse().toString();
        return actual.equals(rev);
    }
}

/*
// version 2: Character.isLetterOrDigit()
public class Solution {
    public boolean isPalindrome(String s) {
        if (s.length() == 0) return true;
        int left = 0, right = s.length() - 1;
        while (left < right) {
            char l = s.charAt(left);
            char r = s.charAt(right);
            if (!Character.isLetterOrDigit(l)) left++;
            else if (!Character.isLetterOrDigit(r)) right--;
            else if (Character.toLowerCase(l) != Character.toLowerCase(r)) return false;
            else {
                left++;
                right--;
            }
        }
        return true;
    }
}

// version 3
public class Solution {
    public boolean isPalindrome(String s) {
        if( s.length() < 2 ) return true;
        
        s = s.toLowerCase();
        int l = s.length();
        StringBuilder sb = new StringBuilder();
        for( int i = 0; i < l; i++ ){
            char c = s.charAt(i);
            if( c >= '0' && c <= '9' || c >= 'a' && c <= 'z'){
                sb.append(c);
            }
        }
        if( sb.length() < 2 ) return true;
        
        int left = 0;
        int right = sb.length() - 1;
        
        while( left < right ){
            char lc = sb.charAt(left);
            char rc = sb.charAt(right);
            if( lc != rc ) return false;
            left++;
            right--;
        }
        return true;
    }
}
*/