/*
* ASCII code order:
*       Number  <  Uppercase letter  <  lowercase letter
*        [ 0 < 9 < A < Z < a < z ]
*/

public class Solution {
    public boolean detectCapitalUse(String word) {
        
        int len = word.length();
        
        if( word == null || len < 1 ){
            return true;
        }
        
        if( len == 1){
            if( word.charAt(0) >= 'A' && word.charAt(0) <= 'Z' ){
                return true;
            }
        }else{
            // uppercase for the 1st letter 
            if( word.charAt(0) >= 'A' && word.charAt(0) <= 'Z' ){ 
            
                // remain should be all uppercase
                if( word.charAt(1) >= 'A'  && word.charAt(1) <= 'Z' ){ 
                    int i = 2;
                    while( i < len ){
                        if( word.charAt(i) > 'Z' ){
                            return false;
                        }
                        i++;
                    }
                // remain should be all lowercase
                }else{ 
                    int i = 2;
                    while( i < len ){
                        if( word.charAt(i) >= 'A' && word.charAt(i) <= 'Z' ){
                            return false;
                        }
                        i++;
                    }
                }
            // lowercase for the 1st letter
            }else{
                int i = 1;
                while( i < len ){
                    if( word.charAt(i) >= 'A' && word.charAt(i) <= 'Z' ){
                        return false;
                    }
                    i++;
                }
            }
        }
        
        return true;
    }
}