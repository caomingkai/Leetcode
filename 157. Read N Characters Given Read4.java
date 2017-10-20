/* The read4 API is defined in the parent class Reader4.
      int read4(char[] buf); */

public class Solution extends Reader4 {
    /**
     * @param buf Destination buffer
     * @param n   Maximum number of characters to read
     * @return    The number of characters read
     */
    
    /*
    // version 1:
    public int read(char[] buf, int n) {
        
        // if file length > n , we should return n
        // if file length < n , we should return the file length
        
        // since we don't know file length, the only way is using read4() when it return is not exactly 4, 
        // we know the file is now with no character
        char[] tempBuff = new char[4];
        int acturalRd = read4(tempBuff);
        int cnt = acturalRd;
        
        while( acturalRd == 4 && cnt < n ){
            for( int i = 0; i < acturalRd; i++ ){
                buf[cnt-acturalRd+1+i] = tempBuff[i];
            }
            acturalRd = read4(buf);
            cnt += acturalRd;
        }
        for( int i = 0; i < acturalRd; i++ ){
            buf[cnt-acturalRd+1+i] = tempBuff[i];
        }
        
        if(  cnt < n ) return cnt;
            
        return n;
    }
    */
    
    public int read(char[] buf, int n) {
        
        char[] buffer = new char[4];
        boolean endOfFile = false;
        int readBytes = 0;
        
        while (readBytes < n && !endOfFile) {
            int currReadBytes = read4(buffer);
            if (currReadBytes !=4) {
                endOfFile = true;
            }
            int length = Math.min(n - readBytes, currReadBytes);
            for (int i=0; i<length; i++) {
                buf[readBytes + i] = buffer[i];
            }
            readBytes += length;
        }
        return readBytes;
    }
}
