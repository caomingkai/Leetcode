// version 1: sort() and comparison
//  since the overhead manipulation for HashSet is not a small deal, it's not so competitive to sort() solution.
public class Solution {
    public boolean containsDuplicate( int[] nums) {
        if( nums == null || nums.length == 0 ) return false;
       
        Arrays.sort(nums);
        int l = nums.length;
        for( int i = 0; i < l - 1; i++ ){
            if( nums[i] == nums[i+1] ) {
                return true;
            }
        }
        return false;
    }
}

/*
// version 2: HashSet
//  no need for contains(), because add() return false if duplicate, and true if no such same value
public class Solution {
    public boolean containsDuplicate( int[] nums) {
        if( nums == null || nums.length == 0 ) return false;
       
        Set< Integer> s = new HashSet<>();
        int l = nums.length;
        for( int i = 0; i < l; i++ ){
            if( !set.add(nums[i]) ) {
                return true;
            }
        }
        return false;
    }
}

*/