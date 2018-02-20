class Solution {
    public int countPrimes(int n) {
        if(n < 3) return 0;
        boolean[] notPrime = new boolean[n];
        int result = 0;
        for(int i = 2; i < n; i++)
        {
            if(notPrime[i]) continue;
            for(int k = 2; k * i < n; k++) notPrime[k * i] = true;
            result++;
        }
        return result;
    }
}