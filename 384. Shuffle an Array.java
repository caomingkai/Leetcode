class Solution {
    private int[] nums;
    private int[] copy;
    Random random = new Random();
    
    public Solution(int[] nums) {
        this.nums = nums;
        this.copy = nums.clone();
    }

    public int[] reset() {
        return copy;
    }

    public int[] shuffle() {
        for (int i = nums.length - 1; i > 0; i--) {
            int j = random.nextInt(i + 1);
            int t = nums[i];
            nums[i] = nums[j];
            nums[j] = t;
        }
        return nums;
    }
}
/**
 * Your Solution object will be instantiated and called as such:
 * Solution obj = new Solution(nums);
 * int[] param_1 = obj.reset();
 * int[] param_2 = obj.shuffle();
 */