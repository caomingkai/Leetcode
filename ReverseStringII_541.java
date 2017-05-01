public class Solution {
    
    // need to declare as instance variable, to be access by inner class
    char[] result;
    
    public String reverseStr(String s, int k) {
        
        if(k < 2){ return s; }
        
        int len = s.length();
        result = s.toCharArray();
        Helper h = new Helper();
        
        for( int i = 0; i < len; i += 2*k){
            if( len  < i + k ){
                h.reverse( i, len -1);
            }else{
                h.reverse(i, i + k - 1);
            }
        }
        
        return new String( result );
    }
    
    
    // inner class can access instance variable!!
    class Helper{
        void reverse( int head, int tail){
            int  mid = (head + tail)/2;
            for(int i = head; i <= mid; i++ ){ // <= rather than <, due to inclusive boundry
                char temp = result[i];
                result[i] = result[head + tail - i];
                result[head + tail - i] = temp;
            }
        }
    }
    
}