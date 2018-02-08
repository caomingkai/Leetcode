/*
    version 1: 低级版本： 1. 未考虑每秒钟会有大量hits
                        2. 使用一个 queue，timestamp按时序压入
                        3. getHits()时候，先进queue的timestamp先出去，直到timestamp<curTime-300
*/
/*
public class HitCounter {
        Queue<Integer> q = null;
        public HitCounter() {
            q = new LinkedList<Integer>();
        }
        
    
        public void hit(int timestamp) {
            q.offer(timestamp);
        }
        
    
        public int getHits(int timestamp) {
            while(!q.isEmpty() && timestamp - q.peek() >= 300) {
                q.poll();
            }
            return q.size();
        }
    }
}
*/



   /*   
        1. 维护一个 300 大小的窗口区，窗口区记录
            - timestamp array
            - 对应的hits array
        2. 随着时间推移，新的timestamp会覆盖窗口区的旧就timestamp以及hits
            - 如何覆盖？
            - timestamp % 300 
        3. getHit() 时，检查窗口区的在[curT-300, curT] 对应timestamp的数据
   
   */
class HitCounter {


    private int[] times;
    private int[] hits;
    
    public HitCounter() {
        times = new int[300];
        hits = new int[300];
    }
    
    /** Record a hit.
        @param timestamp - The current timestamp (in seconds granularity). */
    public void hit(int timestamp) {
        int idx = timestamp%300;
        if( times[idx] != 0 ){             // 该位置有timestamp
            if( times[idx] != timestamp )  // 新值还是旧值？？？
                hits[idx] = 0;
        }
        times[idx] = timestamp;
        hits[idx] += 1;
    }
    
    /** Return the number of hits in the past 5 minutes.
        @param timestamp - The current timestamp (in seconds granularity). */
    public int getHits(int timestamp) {
        int cnt = 0;
        for( int i = 0; i < 300; i++ ){
            if( (timestamp-300)<times[i] && times[i]<=timestamp )
                cnt += hits[i];
        }
        return cnt;
    }
}

/**
 * Your HitCounter object will be instantiated and called as such:
 * HitCounter obj = new HitCounter();
 * obj.hit(timestamp);
 * int param_2 = obj.getHits(timestamp);
 */