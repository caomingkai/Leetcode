public class Solution {
    public String reverseWords(String s) {
        
        int len = s.length();
        int head = 0;   // the first pos of each word
        char[] result = new char[len];
        
        
        for( int i = 0; i < len; i++){
            
            char c = s.charAt(i);
            result[i] = c;
            
            // reverse word once meet a ' '
            if( c == ' ' ){
                for(int j = head; j < (i+head)/2; j++){
                    char temp = result[j];
                    result[j] = result[ i - 1 + head - j ];
                    result[ i - 1 + head - j ] =  temp;
                }
                head = i + 1;
            }
        }
        
        // deal with last word, due to none-whitespace condition
        for(int j = head; j < (len+head)/2; j++){
            char temp = result[j];
            result[j] = result[ len - 1 + head - j ];
            result[ len - 1 + head - j ] =  temp;
        }
                
        return new String(result);
    }
}