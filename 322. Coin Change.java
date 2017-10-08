class Solution {
    public int coinChange(int[] coins, int amount) {
        
        // edge case:
        if( amount == 0 ) return 0;
        
        // minimum changes for each amount of money
        int[] minList = new int[amount+1];
        Arrays.fill( minList, Integer.MAX_VALUE );
        minList[0] = 0; // update amount '0'
        
        // DP: bottom up to calculate the minimum number of coins for each amount of money
        Arrays.sort( coins );
        int n = coins.length;
        
        for( int i = 0; i <= amount; i++ ){
            for( int j = 0;   j < n && coins[j] <= i; j++ ){
                if( minList[i - coins[j]] != Integer.MAX_VALUE ){
                    
                    int option = minList[i - coins[j]] + 1;
                    minList[i] = minList[i] > option ? option : minList[i];
                }
            }
        }
  
        return minList[amount] == Integer.MAX_VALUE ? -1 : minList[amount];
    }
}