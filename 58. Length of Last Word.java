public class Solution {
    /*
    //version 1 : no ready-to-use method of String
    public int lengthOfLastWord(String s) {
        int l = s.length();
        int i = l-1;
        
        while( i >= 0 ){
            if( s.charAt(i) == ' ' ) i--;
            else break;
        }
        
        if( i < 0 ) return 0;
        
        int cnt = 0;
        while( i >= 0 ){
            if( s.charAt(i--) != ' ' ) cnt++;
            else break;
        }
        
        return cnt;
    }
    */
    // version 2: String.trim()   .lastIndexOf(" ")
    public int lengthOfLastWord(String s) {
        return s.trim().length()-s.trim().lastIndexOf(" ")-1;
    }
}