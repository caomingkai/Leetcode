public class Solution {
    public int titleToNumber(String s) {
        
        int l = s.length();
        int n = 0;
        for( int i = 0; i < l; i++)
        {
            char c = s.charAt(i);
            n *= 26;
            n += c - 64;    // 'A' = 65
        }
        
        return n;
    }
}