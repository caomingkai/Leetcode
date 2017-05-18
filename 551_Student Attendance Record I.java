public class Solution {
    public boolean checkRecord(String s) {
        
        int cntA = 0;
        boolean flag1 = false; // indicate last one is  'L'
        boolean flag2 = false; // indicate last two are 'L'
        
        int len = s.length();
        int i = 0;
        
        while( i < len ){
            char temp = s.charAt(i);
            
            if( temp == 'A'){
                i++;
                cntA++;
                if( cntA > 1){return false;}
                flag1 = false;
                flag2 = false;
                
            }else if( temp == 'L'){
                i++;
                if(flag2 == true){ // indicate last two are both 'L', plus this one, return false
                    return false;
                }
                
                if(flag1 == true){ // indicate last one is 'L', plus this one, flag2 = false
                    flag2 = true;
                }
                
                flag1 = true;     // tell next one: the prior one is 'L'
                
            }else{
                i++;
                flag1 = false;
                flag2 = false;
            }
        }
        return true;
    }
}