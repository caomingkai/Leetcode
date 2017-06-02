/*
// version 1: bitwise manipulation
public class Solution {
    // you need treat n as an unsigned value
    public int reverseBits(int n) {
        int r = 0;          // reverserd number
        int w = 1;          // bit weight
        int f = 1 << 31;    // filter: Most Significant Bit
        for( int i = 0; i < 32; i++ ){
            if( (n&f) != 0 ) r +=  w;
            //w = w * 2;    // can be done by bit manipulation
            w = w << 1;
            n = n << 1;
        }
        return r;
    }
}
*/

// version 2: hashmap
// speed improvement routine: trade off Space for Time
// make record of all the 8-bit pattern for later look-up, no need to do calculation again after 1st time calculation.
public class Solution {
    // you need treat n as an unsigned value
    private final Map<Byte, Integer> cache = new HashMap<Byte, Integer>();
    public int reverseBits(int n) {
// Make use of 'final' HashMap to store the info 
// key: original bit pattern
// value: integer for 8-bit-pattern after convertion
        
        byte[] bytes = new byte[4];
        for (int i = 0; i < 4; i++) // convert int into 4 bytes
            bytes[i] = (byte)((n >>> 8*i) & 0xFF);
        int result = 0;
        for (int i = 0; i < 4; i++) {
            result += reverseByte(bytes[i]); // reverse per byte
            if (i < 3)
                result <<= 8;
        }
        return result;
    }


    private int reverseByte(byte b) {
        Integer value = cache.get(b); // first look up from cache
        if (value != null)
            return value;
        value = 0;
        // reverse by bit
        for (int i = 0; i < 8; i++) {
            value += ((b >>> i) & 1);
            if (i < 7)
                value <<= 1;
        }
        cache.put(b, value);
        return value;
    }

}