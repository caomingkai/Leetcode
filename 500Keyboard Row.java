// HashMap

public class Solution {
    public String[] findWords(String[] words) {
        
        int l = words.length;
        String[] res = new String[l];
        
        Map<Character, Integer> kb = new HashMap<>();
        int[] r = {2,3,3,2,1,2,2,2,1,2,2,2,3,3,1,1,1,1,2,1,1,3,1,3,1,3};
        char[] c = new char[26];
        for( int i = 0; i < 26; i++){
            c[i] = (char)(i + 97);
        }
        
        // put character(key) with its row(value)
        for( int i = 0; i < 26; i++){
            kb.put( c[i] , r[i] );
        }
        
        int k = 0; // for res
        // check each word
        for( int i = 0; i < l; i++){
            String current = words[i];
            String cur = current.toLowerCase();
            int row = kb.get( cur.charAt(0) );
            int len = cur.length();
            int j = 1;
            
            while( j < len && row == kb.get(cur.charAt(j)) ){
                j++;
            }
            
            if( j == len){
                System.out.println("asdfsd");
                res[k] = current;
                k++;
            }
        }
        return Arrays.copyOfRange(res, 0 , k);
    }
}