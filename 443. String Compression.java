class Solution {
    public int compress(char[] chars) {
        if( chars == null || chars.length == 0 )
            return 0;
        
        int len = chars.length;
        char prev = chars[0];
        int cnt = 1;
        int idx = 0;
        
        int i = 1;
        while( i < len ){
            char curr = chars[i];
            if( curr == prev )
                cnt++;
            else{
                chars[idx++] = prev; // update the character in-place 
                if( 1 < cnt && cnt < 10 ){      // update the digit in-place
                    chars[idx++] = (char)( cnt+'0'); 
                }else if( cnt >= 10) {
                    char[] digits = String.valueOf(cnt).toCharArray();
                    for( int k = 0; k < digits.length; k++ )
                        chars[idx++] = digits[k];
                }
                prev = chars[i];
                cnt = 1;
            }
            i++;
        }
        
        // 
        chars[idx++] = prev;            // update the character in-place
        if( 1 < cnt && cnt < 10 ){      // update the digit in-place
            chars[idx++] = (char)( cnt +'0'); 
        }else if( cnt >= 10) {
            char[] digits = String.valueOf(cnt).toCharArray();
            for( int k = 0; k < digits.length; k++ )
                chars[idx++] = digits[k];
        }
        
        return idx--;
    }
}