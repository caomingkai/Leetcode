public class Solution {
    //if n <= 0, then the number of ways should be zero.
    //if n == 1, then there is only way to climb the stair.
    //if n == 2, then there are two ways to climb the stairs. One solution is one step by another; the other one is two steps at one time.
    public int climbStairs(int n) {
        // base cases
        if(n <= 0) return 0;
        if(n == 1) return 1;
        if(n == 2) return 2;

        int one_step_before = 2;
        int two_steps_before = 1;
        int all_ways = 0;

        for(int i=2; i<n; i++){
            all_ways = one_step_before + two_steps_before;
            two_steps_before = one_step_before;
            one_step_before = all_ways;
        }
        return all_ways;
    }
}