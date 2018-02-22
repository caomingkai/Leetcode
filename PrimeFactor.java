import java.util.*;


class PrimeFactor{

	public static void main( String[] args ){
		int n = 1000;
		printPrime(n);
	}

	// version 1 : iteration
	private static void printPrime( int n ){
	    // Print the number of 2s that divide n
	    while (n%2 == 0)
	    {
	       System.out.println(2);
	        n = n/2;
	    }
	 
	    // n must be odd at this point.  So we can skip 
	    // one element (Note i = i +2)
	    for (int i = 3; i <= Math.sqrt(n); i = i+2)
	    {
	        // While i divides n, print i and divide n
	        while (n%i == 0)
	        {
	            System.out.println(i);
	            n = n/i;
	        }
	    }
	 
	    // This condition is to handle the case when n 
	    // is a prime number greater than 2
	    if (n > 2)
	        System.out.println(n);
	}


/*
	version 2: recursion
	原理：每一个数都可以由其前(包括自身)的prime乘积得到！
		 如 “12”可以由 2*2*4 得到

		0. 从 2 -> n，挨个整除，
		   - 不必担心有非prime在内，即便有，也不会被其整除，
		   - 因为已经由其之前的prime整除完了，所以不会统计进去
	    1. 先找出所有的 ‘2’因子，直到不能被2整除
	    2. 继续找所有的 ‘3’因子，直到不能被3整除
	    3. 继续找所有的 ‘4’因子，不可能因为2已经被统计完了
	    4. 继续找所有的 ‘5’因子，直到不能被5整除
	        ....
*/
	/*
	private static void printPrime( int n ){
		if( n == 1 ) return; // 最后整除到‘1’时，返回

		boolean flag = false;
		int i = 2;
		for( ;i <= n; i++){  // 找到第一个prime就跳出
			if( n%i == 0 )
				break;
		}
		System.out.println(i);// 先打印
		printPrime(n/i);	  // 后打印‘商’的prime
	}
	*/
}