class CubeRoot{

	public static void main( String[] args ){
		int n = 125;
		System.out.println(cbrt(n));
	}


	private static double cbrt( int n ){
		double ret = 0.0;
		double e = 0.0000001;
		boolean f = false;

		if( n < 0 ){
			n = -n;
			f = true;
		}

		double l = 0.0;
		double h = n;
		while(true){
			double m = l + (h-l)/2;
			if( m*m > n/m ){
				h = m;
			}else{
				double error = diff( m, n );
				if( error < e ){
					ret = m;
					break;
				}
				l = m;
			}
		}
		if(f) ret = -ret;

		return ret;
	}

	private static double diff( double m, int n ){
		if( m*m*m > n )
			return m*m*m - n;
		return n - m*m*m;
	}
}



