// version 1: bucket hash for char
public class Solution {
    public boolean isIsomorphic(String s, String t) {
        int[] map = new int[256];    // actually store two arrays in two piece of 256 memory
        
        for (int i = 0; i < s.length(); i++) {
            int sIndex = s.charAt(i);
            int tIndex = t.charAt(i) + 128;
            
            if (map[sIndex] != map[tIndex]) {
                return false;
            }
            
            map[sIndex] = i + 1;  // since all elements map is initialized to '0'
            map[tIndex] = i + 1; 
        }
        
        return true;
    }
}
/*
// version 2: hashmap
public class Solution {
    public boolean isIsomorphic(String s, String t) {
        if( s.length() == 0 ) return true;
        
        int l = s.length();
        
        Map<Character, Character> maps = new HashMap<>();
        Map<Character, Character> mapt = new HashMap<>();
        
        for( int i = 0; i < l; i++){
            if( maps.containsKey( s.charAt(i)) ){
                if( t.charAt(i) != maps.get( s.charAt(i) ) ) return false;
            }else{
                maps.put( s.charAt(i), t.charAt(i) );
            }
            
            if( mapt.containsKey( t.charAt(i)) ){
                if( s.charAt(i) != mapt.get( t.charAt(i) ) ) return false;
            }else{
                mapt.put( t.charAt(i), s.charAt(i) );
            }
            
        }
        return true;
    }
}
*/