/* 
    Moorer Voting Algo
    1）当前元素与假定Majority Element相等时，计数器加1。

    2）当目前元素的计数器为0是，说明是被消除者，重新定Majority Element。

    3）如果与假定的Majority Element不相等时，同时消除一组，此时为3个元素，
       即消除两个假定的Majority Element，两个计数器同时减1。

    4）最后不要忘记判断得出来的元素是否为Majority Element
    5）先判断是否与candidate相同，再判断cnt： 避免“连续的相同值元素”同时成为candidate

*/

class Solution {
    public List<Integer> majorityElement(int[] nums) {  
        List<Integer> ret = new ArrayList<Integer>();  
        if (nums == null || nums.length == 0) return ret;  
        int num1 = 0, num2 = 0, cnt1 = 0, cnt2 = 0;  
        for (int i = 0; i < nums.length; i++) {  
            if (num1 == nums[i]) cnt1++;  
            else if (num2 == nums[i]) cnt2++;  
            else if (cnt1 == 0) {  
                cnt1++;  
                num1 = nums[i];  
            } else if (cnt2 == 0) {  
                cnt2++;  
                num2 = nums[i];  
            } else {  
                cnt1--;  
                cnt2--;  
            }  
        }  
        cnt1 = 0;  
        cnt2 = 0;  
        for (int i = 0; i < nums.length; i++) {  
            if (num1 == nums[i]) cnt1++;  
            else if (num2 == nums[i]) cnt2++;  
        }  
        if (cnt1 > nums.length/3) ret.add(num1);  
        if (cnt2 > nums.length/3) ret.add(num2);  
        return ret;  
    } 
}