// bucket hash
public class Solution {
    public boolean isAnagram(String s, String t) {
        int ls = s.length();
        int lt = t.length();
        if( ls != lt ) return false;
        
        int[] rec = new int[26];
        for( int i = 0; i < ls; i++ ){
            char cs = s.charAt(i);
            char ct = t.charAt(i);
            rec[ cs - 'a' ]++;
            rec[ ct - 'a' ]--;
        }
        
        for( int i = 0; i < 26; i++){
            if( rec[i] != 0 ) return false;
        }
        
        return true;
    }
}