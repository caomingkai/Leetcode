
// version 1: one scan
public class Solution {
    public int firstUniqChar(String s) {
        int[] alphabet = new int[26];
        Arrays.fill(alphabet, -1);
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (alphabet[c - 'a'] == -1) { // first time letter
                alphabet[c - 'a'] = i;
            } else {
                alphabet[c - 'a'] = -2; //  mark as repeated letter
            }
        }
        int first = s.length();
        for (int index : alphabet) {
            if (index >= 0 && index < first) {
                first = index;
            }
        }
        return first == s.length() ? -1 : first;
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