

/*
Question: 

Given a 2^31 x 2^31 tic tac toe board, 
describe how you would store the state of the game to check if there is a winner.

		规则：第一个连成3个一列、一行、对角线的人获胜
		状态：X | O | Empty

			 |     |
		  X  |	   |
		_____|_____|_____
	 		 |     | 	 
	 	  O  |  X  |  O
		_____|_____|_____
		   	 |	   |  
		  O  |	O  |  X
			 |     |



	 对于 2^31 * 2^31 大的棋盘，需要 2^31 * 2^31位置存储状态， 
	 signed int: -2^31 ~ 0 ~ 2^31-1
						 |     |
					  int[Integer.MAX_VALUE] 数组从[0,Integer.MAX_VALUE] 恰好可以达到2^31
	 

	 两种方案：
	 	    方案一： int[Integer.MAX_VALUE][Integer.MAX_VALUE]      
	 	    		1. 每个位置用一个 int 表示： "O"->0 ; "X"->1; "E"->2
	 	    		2. 空间： 2^31 * 2^31 * 32bit 

	 	    方案二： int[Integer.MAX_VALUE][Integer.MAX_VALUE / 32 * 2]
	 	    		1. 每个位置用两个 bit 表示： "O"->00; "X"->11; "E"->01 (or 10)
	 	    		   一个Integer只能表示16个column的状态
	 	    		2. 空间： 2^31 * 2^31 * 2bit
 

	 	    方案三： 两个 int[Integer.MAX_VALUE][Integer.MAX_VALUE/32]
	 	    		1. 一个表示"O"玩家；另一个表示"X"玩家
	 	    		2. "O"玩家： 填"O"的地方存 bit：0， 其余地方存 bit：1
	 	    		3. "1"玩家： 填"1"的地方存 bit：1， 其余地方存 bit：0
	 	    			一个Integer只能表示32个column的状态
	 	    		4. 对两个表同时检查状态：00->"O"； 11->"X"； 01->"E"； 10->"E"
	 	    		5. 空间： 2^31 * 2^31 * 1bit * 2个矩阵



*/
