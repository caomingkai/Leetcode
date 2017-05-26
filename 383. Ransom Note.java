public class Solution {
    public boolean canConstruct(String ransomNote, String magazine) {
        
        int l1 = ransomNote.length();
        int l2 = magazine.length();
        
        if( l1>l2 ) return false;
        
        int[] dict = new int[26];
        
        for(int i = 0; i < l2; i++){
            dict[ magazine.charAt(i) - 'a' ]++;
        }
        
        for( int i = 0; i < l1; i++){
            if( --dict[ ransomNote.charAt(i) - 'a' ] < 0) return false;
        }
        
        return true;
    }
}