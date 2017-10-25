/**
 * Definition for a point.
 * class Point {
 *     int x;
 *     int y;
 *     Point() { x = 0; y = 0; }
 *     Point(int a, int b) { x = a; y = b; }
 * }
 */

/*
// version 1: double:lack precision  ---> error
public class Solution {
    public int maxPoints(Point[] points) {
        if(points.length <= 0) return 0;
        if(points.length <= 2) return points.length;
        int result = 0;
        for(int i = 0; i < points.length; i++){
            HashMap<Double, Integer> hm = new HashMap<Double, Integer>();
            int samex = 1;
            int samep = 0;
            for(int j = 0; j < points.length; j++){
                if(j != i){
                    if((points[j].x == points[i].x) && (points[j].y == points[i].y)){
                        samep++;
                    }
                    if(points[j].x == points[i].x){
                        samex++;
                        continue;
                    }
                    double k = (double)(points[j].y - points[i].y) / (double)(points[j].x - points[i].x);
                    System.out.println(k);
                    if(hm.containsKey(k)){
                        hm.put(k,hm.get(k) + 1);
                    }else{
                        hm.put(k, 2);
                    }
                    result = Math.max(result, hm.get(k) + samep);
                    
                }
            }
            result = Math.max(result, samex);
        }
        return result;
    }
}
*/


    // version 2: utilize the unique x-y pair as slope ratio by using GCD
    public class Solution{
        public int maxPoints(Point[] points) {
        	if (points==null) return 0;
        	if (points.length<=2) return points.length;
        	
        	Map<Integer,Map<Integer,Integer>> map = new HashMap<Integer,Map<Integer,Integer>>();
        	int result=0;
        	for (int i=0;i<points.length;i++){ 
        		map.clear();
        		int overlap=0,max=0;
        		for (int j=i+1;j<points.length;j++){
        			int x=points[j].x-points[i].x;
        			int y=points[j].y-points[i].y;
        			if (x==0&&y==0){
        				overlap++;
        				continue;
        			}
        			int gcd=generateGCD(x,y);
        			if (gcd!=0){
        				x/=gcd;
        				y/=gcd;
        			}
        			
        			if (map.containsKey(x)){
        				if (map.get(x).containsKey(y)){
        					map.get(x).put(y, map.get(x).get(y)+1);
        				}else{
        					map.get(x).put(y, 1);
        				}   					
        			}else{
        				Map<Integer,Integer> m = new HashMap<Integer,Integer>();
        				m.put(y, 1);
        				map.put(x, m);
        			}
        			max=Math.max(max, map.get(x).get(y));
        		}
        		result=Math.max(result, max+overlap+1);
        	}
        	return result;
        	
        	
        }
        private int generateGCD(int a,int b){
    
        	if (b==0) return a;
        	else return generateGCD(b,a%b);
        	
        }
    }
    
