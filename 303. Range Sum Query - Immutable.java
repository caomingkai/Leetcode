

// version 1: Inefficient solution: every time call to the sumRange(), do the loop calculation  
// --- (  many loops : time consuming )
// version 2: improvement: at beginning do the accumulative sum; then every time call sumRange(), only need do subtraction
// --- ( only 1 loop : at the beginning )
// version 3: most efficient
// class representation:  use int[] instead of ArrayList<Integer>, for it's instance variable, can be initialized in constructor
/*

public class NumArray {
    private ArrayList<Integer> n = new ArrayList<>();
    
    public NumArray(int[] nums) {
        int l = nums.length;
        for( int i = 0; i < l; i++ ){
            n.add( nums[i] );
        }
    }
    
    public int sumRange(int i, int j) {
        int sum = 0;
        for( int k = i; k <= j; k++ ){
            sum += n.get(k);
        }
        return sum;
    }
}

*/

/*
// version 2:
public class NumArray {
    private ArrayList<Integer> s = new ArrayList<>();  // accumulative sum
    
    public NumArray(int[] nums) {
        int l = nums.length;
        int sum = 0;
        for( int i = 0; i < l; i++ ){
            sum += nums[i];
            s.add( sum );
        }
    }
    
    public int sumRange(int i, int j) {
        if( i == 0 ) return s.get(j);
        return s.get(j) - s.get(i-1);
    }
}
*/


// version 3:
public class NumArray {
    private int[] s;  // accumulative sum
    
    public NumArray(int[] nums) {
        int l = nums.length;
        if( l != 0 ){
            s = new int[l];
            s[0] = nums[0];
            for( int i = 1; i < l; i++ ){
                s[i] = s[i-1] + nums[i];
            }
        }
    }
    
    public int sumRange(int i, int j) {
        if( i == 0 ) return s[j];
        return s[j] - s[i-1];
    }
}
/**
 * Your NumArray object will be instantiated and called as such:
 * NumArray obj = new NumArray(nums);
 * int param_1 = obj.sumRange(i,j);
 */