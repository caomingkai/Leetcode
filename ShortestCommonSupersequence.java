import java.util.*;

/*
	1. 先找 longest common sequence 的长度
		
		- 子问题:
			Let LCS[i, j] be the length of the LCS of S[1 · · ·i] with T[1 · · · j]
		
		- 递推式
			Case 1. S[i] = T[j],  LCS[i, j] = 1 + LCS[i-1, j-1]
				不难用反证法证明：该字符必是X与Y的任一最长公共子序列Z（设长度为k）的最后一个字符，即有zk = xm = yn 且显然有Zk-1∈LCS(Xm-1 , Yn-1)即Z的前缀Zk-1是Xm-1与Yn-1的最长公共子序列。此时，问题化归成求Xm-1与Yn-1的LCS（LCS(X , Y)的长度等于LCS(Xm-1 , Yn-1)的长度加1）。
			Case 2. S[i] ≠ T[j],  LCS[i, j] = max{ LCS[i-1, j], LCS[i, j-1] }
		
		- 伪代码
			int LCS(char[] S, int n, char[] T, int m)
			{
				int table[n+1, m+1];
				table[0…n, 0] = table[0, 0…m] = 0; //init
				for(i = 1; i <= n; i++)
					for(j = 1; j <= m; j++)
						if (S[i] == T[j]) 
							table[i, j] = 1 + table[i-1, j-1]
						else
							table[i, j] = max(table[i, j-1], table[i-1, j]);
				return table[n, m];
			}

	2. 根据长度回溯找到这个 sequence
		int i = len1;
		int j = len2
		while( dp[i][j] != 0 ){
			if( s1[i] != s2[j] ){
				dp[i][j] = Math.max( dp[i-1][j], dp[i][j-1] );
			}else{
				sq.appendFront(s1[i]);
				dp[i][j] = dp[i-1][j-1];
			}
		}

	3. 扫描seq的每个字符 c 为准
		- 扫描s1：
			- 与 c 不一样的字符append到res后
			- 直到遇到与c 一样的跳出循环
		- 扫描s2:
			- 与 c 不一样的字符append到res后
			- 直到遇到与c 一样的跳出循环

		int k = 0;
		int j = 0;
		for( int i = 0; i < seq.length(); i++ ){
			while( k < len1 ){
				if( s1.charAt(k) != seq.charAt(i) )
					res.append(s1.charAt(k));
				else
					break;
			}
			
			while( j < len2 ){
				if( s2.charAt(j) != seq.charAt(i) )
					res.append(s2.charAt(j));
				else
					break;
			}

		}

*/

class ShortestCommonSupersequence{
	public static void main( String[] args ){
		String str1 = "geek";
		String str2 = "eke";

		System.out.println( getShortestSequence(str1, str2));
	}


	public static String getShortestSequence( String s1, String s2){
		
	}
}