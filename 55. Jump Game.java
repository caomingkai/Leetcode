/*
// version 1: backtracking 

class Solution {
    public boolean canJump(int[] nums) {
        
        int l = nums.length;
        
        return jumpHelp( nums, 0 ) ;
    }
    
    
    private boolean jumpHelp( int[] nums, int index ){
        
        System.out.println(index);
        int l = nums.length;
        
        if( index >= l ) 
            return true;
        
        for( int i = 1; i <= nums[index]; i++ ){
            if( index+i >= l-1 )
                return true;
            
            if( nums[index+i] != 0 )
                if( jumpHelp( nums, index+i ) )
                    return true;
        }
        
        return false;
    }
}

*/

/*
// version 2: DP top-down
class Solution {
    
    enum Index {
        GOOD, BAD, UNKNOWN
    }
    
    Index[] memo;
    
    public boolean canJump(int[] nums) {
        int l = nums.length;
        
        memo = new Index[nums.length];
        for( int i = 0; i < l; i++ ){
            memo[i] = Index.UNKNOWN;
        }
        
        return jumpHelp( nums, 0 ) ;
    }
    
    
    private boolean jumpHelp( int[] nums, int index ){
        
        int l = nums.length;
        
        if( memo[index] != Index.UNKNOWN )
            return memo[index] == Index.GOOD;
            
        if( index >= l ) {
            memo[index] = Index.GOOD;
            return true;
        }
        for( int i = 1; i <= nums[index]; i++ ){
            if( index+i >= l-1 ){
                memo[index] = Index.GOOD;
                return true;
            }
            
            if( nums[index+i] != 0 ){
                if( jumpHelp( nums, index+i ) ){
                    memo[index+i] = Index.GOOD;
                    return true;
                }
            }else{
                memo[index] = Index.BAD;
            }
        }
        
        return false;
    }
}
*/


/*
// version 3: DP bottom-up
enum Index {
    GOOD, BAD, UNKNOWN
}

public class Solution {
    public boolean canJump(int[] nums) {
        Index[] memo = new Index[nums.length];
        for (int i = 0; i < memo.length; i++) {
            memo[i] = Index.UNKNOWN;
        }
        memo[memo.length - 1] = Index.GOOD;

        for (int i = nums.length - 2; i >= 0; i--) {
            int furthestJump = Math.min(i + nums[i], nums.length - 1);
            for (int j = i + 1; j <= furthestJump; j++) {
                if (memo[j] == Index.GOOD) {
                    memo[i] = Index.GOOD;
                    break;
                }
            }
        }

        return memo[0] == Index.GOOD;
    }
}
*/


//version 4: Greedy
public class Solution {
    public boolean canJump(int[] nums) {
        int lastPos = nums.length - 1;
        for (int i = nums.length - 1; i >= 0; i--) {
            if (i + nums[i] >= lastPos) {
                lastPos = i;
            }
        }
        return lastPos == 0;
    }
}