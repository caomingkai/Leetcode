class Solution {
    public int leastInterval(char[] tasks, int n) {
        int res = 0;
        
        // edge case: no
        
        // general case:
        
        // 1. record occurence frequence for each char [bucket sort]
        int[] map = new int[26];
        int len = tasks.length;
        for( int i = 0; i < len; i++ ){
            int index = tasks[i] - 'A';
            map[index]++;
        }
        // 2. sort map in ascending order, so map[25] has the max frequence
        Arrays.sort(map);
        // 3.1 we needn't not care which one is which one, just knowing they are different is OK.
        // 3.2 from max frequence char to next (n-1) characters, no matter there nothing left, as long as tasks[25] is not "0"
        // 3.2 we need to loop through all the characters till tasks[25] == 0
        
        while( map[25] != 0 ){
            
            int i = 0;
            while( i <= n ){
                if(map[25] == 0)    // in case the the 25th one become "0" inside the loop, so no need to increment the interval
                    break;
                if(i<26)            // in case "n" is larger than 26, avoiding index out of boundry 
                    map[25-i]--;
                i++;
                res++;
            }
            Arrays.sort(map);
        }
        
        return res;
    }
}