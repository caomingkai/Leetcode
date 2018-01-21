class Solution {
    public void reverseWords(char[] str) {
        
        reverse(0, str.length-1, str);
        int wStart = 0;
        int wEnd = 0;
        int i = 0;
        int l = str.length;
        while( i < l ){
            if( str[i] == ' ' ){
                wEnd = i-1;
                reverse( wStart, wEnd, str );
                wStart = i+1;
            }
            i++;
        }
        
        // since there is no whitespace in the end, we need to deal it seperately
        wEnd = l-1;
        reverse( wStart, wEnd, str );
    }
    
    private void reverse( int s, int e, char[] str){
        while( s < e ){
            char temp = str[s];
            str[s++] = str[e];
            str[e--] = temp;
        }
    }

}