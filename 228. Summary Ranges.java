class Solution {
    public List<String> summaryRanges(int[] nums) {
        
        List<String> res = new LinkedList<>();
        
        // edge case
        if( nums == null || nums.length == 0 )
            return res;
        if( nums.length == 1 ){
            res.add(String.valueOf(nums[0]));
            return res;
        }
            
        
        //general case 
        int len = nums.length;
        int head = nums[0];
        int tail = nums[0];
        
        for( int i = 1; i < len; i++ ){
            if( nums[i] != tail + 1 ){
                String cur = "";
                if( head == tail ){
                    cur += head;
                }else{
                    cur = head + "->" + tail;
                }
                res.add(cur);
                head = nums[i];
                tail = nums[i];
            }else{
                tail = nums[i];
            }
        }
        String cur = "";
        if( head == tail ){
            cur += head;
        }else{
            cur = head + "->" + tail;
        }
        res.add(cur);
        return res;
    }
}

/*
// version 2:
public class Solution {
    public List<String> summaryRanges(int[] nums) {
        List<String> summary = new ArrayList<>();
        for (int i = 0, j = 0; j < nums.length; ++j) {
            // check if j + 1 extends the range [nums[i], nums[j]]
            if (j + 1 < nums.length && nums[j + 1] == nums[j] + 1)
                continue;
            // put the range [nums[i], nums[j]] into the list
            if (i == j)
                summary.add(nums[i] + "");
            else
                summary.add(nums[i] + "->" + nums[j]);
            i = j + 1;
        }
        return summary;
    }
}
*/