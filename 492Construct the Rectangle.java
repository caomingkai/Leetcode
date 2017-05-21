public class Solution {
    public int[] constructRectangle(int area) {
        
        if(area <= 0 ){
            return new int[]{0,0};
        }
        
        int w = (int)Math.sqrt( (double) area );
        
        while( area % w != 0 ){
            w--;
        }
        
        int l = area / w;
        return new int[]{l, w};
    }
}