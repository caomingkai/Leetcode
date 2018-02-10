
// version 1: LinkedHashMap + two scan
public class Solution {
    public int firstUniqChar(String s) {
        
        Map<Character, Integer> charFreq = new LinkedHashMap<>();
        char[] sArr = s.toCharArray();
        int len = sArr.length;
        
        for( char c : sArr )
            charFreq.put( c, charFreq.getOrDefault(c, 0)+1 );
        
        for( int i = 0; i < len; i++ ){
            if( charFreq.get(sArr[i]) == 1 )
                return i;
        }
        return -1;
    }
}

/*
// version 2: hashmap + two scan
public class Solution {
    public int firstUniqChar(String s) {
        
        int l = s.length();
        
        int[] dict = new int[26];
        
        for( int i = 0; i < l; i++){
            dict[ s.charAt(i) - 'a' ]++;
        }
        
        for( int i = 0; i < l; i++){
             if( dict[ s.charAt(i) - 'a' ] == 1){
                 return i;
             }
        }
        
        return -1;
    }
}
*/