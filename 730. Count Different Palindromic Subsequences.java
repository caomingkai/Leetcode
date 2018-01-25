/*
    key idea: 从外向里，一层一层，剥洋葱！
    - 因为subsequence，必须要由两个相同字母在前后，才valid； 
    - 从外层往里看，找到第一对相同字母
    
    
    when s.charAt(i) != s.charAt(j):
        dp[i][j] = dp[i][j] = dp[i][j - 1] + dp[i + 1][j] - dp[i + 1][j - 1];

    When s.charAt(i) == s.charAt(j): see code comments
        
*/

class Solution {
    public int countPalindromicSubsequences(String s) {
        int len = s.length();
        int[][] dp = new int[len][len];

        char[] chs = s.toCharArray();
        for(int i = 0; i < len; i++){
            dp[i][i] = 1;   // Consider the test case "a", "b" "c"...
        }

        for(int distance = 1; distance < len; distance++){
            for(int i = 0; i < len - distance; i++){
                int j = i + distance;
                if(chs[i] == chs[j]){
                    int low = i + 1;
                    int high = j - 1;

              /* Variable low and high here are used to get rid of the duplicate*/

                    while(low <= high && chs[low] != chs[j]){
                        low++;
                    }
                    while(low <= high && chs[high] != chs[j]){
                        high--;
                    }
                    if(low > high){
                        // consider the string from i to j is "a...a" "a...a"... where there is no character 'a' inside the leftmost and rightmost 'a'
                       /* eg:  "aba" while i = 0 and j = 2:  dp[1][1] = 1 records the palindrome{"b"}, 
                         the reason why dp[i + 1][j  - 1] * 2 counted is that we count dp[i + 1][j - 1] one time as {"b"}, 
                         and additional time as {"aba"}. The reason why 2 counted is that we also count {"a", "aa"}. 
                         So totally dp[i][j] record the palindrome: {"a", "b", "aa", "aba"}. 
                         */ 

                        dp[i][j] = dp[i + 1][j - 1] * 2 + 2;  
                    } 
                    else if(low == high){
                        // consider the string from i to j is "a...a...a" where there is only one character 'a' inside the leftmost and rightmost 'a'
                       /* eg:  "aaa" while i = 0 and j = 2: the dp[i + 1][j - 1] records the palindrome {"a"}.  
                         the reason why dp[i + 1][j  - 1] * 2 counted is that we count dp[i + 1][j - 1] one time as {"a"}, 
                         and additional time as {"aaa"}. the reason why 1 counted is that 
                         we also count {"aa"} that the first 'a' come from index i and the second come from index j. So totally dp[i][j] records {"a", "aa", "aaa"}
                        */
                        dp[i][j] = dp[i + 1][j - 1] * 2 + 1;  
                    }
                    else{
                        // consider the string from i to j is "a...a...a... a" where there are at least two character 'a' close to leftmost and rightmost 'a'
                       /* eg: "aacaa" while i = 0 and j = 4: the dp[i + 1][j - 1] records the palindrome {"a",  "c", "aa", "aca"}. 
                          the reason why dp[i + 1][j  - 1] * 2 counted is that we count dp[i + 1][j - 1] one time as {"a",  "c", "aa", "aca"}, 
                          and additional time as {"aaa",  "aca", "aaaa", "aacaa"}.  Now there is duplicate :  {"aca"}, 
                          which is removed by deduce dp[low + 1][high - 1]. So totally dp[i][j] record {"a",  "c", "aa", "aca", "aaa", "aaaa", "aacaa"}
                          */
                        dp[i][j] = dp[i + 1][j - 1] * 2 - dp[low + 1][high - 1]; 
                    }
                }
                else{
                    dp[i][j] = dp[i][j - 1] + dp[i + 1][j] - dp[i + 1][j - 1];  //s.charAt(i) != s.charAt(j)
                }
                dp[i][j] = dp[i][j] < 0 ? dp[i][j] + 1000000007 : dp[i][j] % 1000000007;
            }
        }

        return dp[0][len - 1];
    }
}


/*  
    //  VERSION 2:  BAD IDE: O(2^N)
    
    1. traverse all the subsequences
        1.1 String length is l, then there are 2^l possibilities, 
        1.2 " use it => 1 " or "don't use it => 0 ", abacd: 11111 ; abcd: 11011; ad: 10001
        1.3 0000 ~ 11111( 2^l - 1 )
        1.4 based on each pattern, we extract out the subsequence
            1 0 0 1 1   <-- pattern 
            1 2 1 3 4   <-- string 
           ------------
            1   1 3 4   <-- subsequence
    
    2. check if a subsequence is palindrom or not
        2.1 since only four letters are used, we use 1,2,3,4 to convert into integer
        2.2 convert into two integers: front-to-back Int and back-to-front Int
        2.3 check if they are equal
*/
