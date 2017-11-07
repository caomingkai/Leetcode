class Solution {
    public int myAtoi(String str) {
        double res = 0;
        boolean flag = false;
        
        if( str == null || str.length() == 0 ) return 0;
        
        int len = str.length();
        int i = 0;
        while(  i < len){
            if( str.charAt(i) != ' ' ) 
                break;
            i++; 
        }
        
        char c = str.charAt(i);
        if( c == '+' ){
            flag = false;
            i++;
        }else if( c == '-' ){
            flag = true;
            i++;
        }else if( c < '0' || c > '9' )
            return 0;
        else
            flag = false;

        while( i < len ){
            c = str.charAt(i);
            if( c >= '0' && c <= '9' ){
                res = res*10 + c-'0';
            }else{
                break;
            }
            i++;
        }
        if( flag == true ){
            if( res - 1 >= Integer.MAX_VALUE)
                return Integer.MIN_VALUE;
        }else{
            if( res  >= Integer.MAX_VALUE)
                return Integer.MAX_VALUE;
        }
        
        return flag == false ? (int)res : (int)(-res);
    }
}