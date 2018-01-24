class Solution {
    public boolean canPlaceFlowers(int[] flowerbed, int n) {
        if( n == 0 ) return true;
        
        int l = flowerbed.length;
        for( int i = 0; i < l; i++ ){
            if( flowerbed[i] == 0 ){
                // check if any side has flower, we just skip to next position
                if( ( i-1 >=0 && flowerbed[i-1]==1 )  ||  ( i+1 < l && flowerbed[i+1]==1 )  )
                    continue;
                n--;
                if( n <= 0 )     // <=  instead of == , because maybe there are more vacant spots
                    return true;
                flowerbed[i] = 1;
            }
        }
        return false;    
    }

}